package booking_policy_service

import company_service.company.CompanyId
import company_service.company.CompanyRepository
import company_service.employee.EmployeeId
import company_service.employee.EmployeeRepository
import hotel_service.hotel.room.RoomType

class BookingPolicyService(
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository
) {
    fun setCompanyPolicy(companyId: CompanyId, roomTypes: Set<RoomType>) {
        companyRepository.getById(companyId).addPolicies(roomTypes.map { BookingPolicy(it) }.toSet())
    }

    fun setEmployeePolicy(employeeId: EmployeeId, roomTypes: Set<RoomType>) =
        employeeRepository.getById(employeeId).addPolicies(roomTypes.map { BookingPolicy(it) }.toSet())

    fun isBookingAllowed(employeeId: EmployeeId, roomType: RoomType): Boolean {
        val employee = employeeRepository.getById(employeeId)
        val company = companyRepository.getById(employee.companyId)

        val policies = company.policies().plus(employee.policies())

        if (policies.isEmpty()) {
            return true
        }

        return policies.contains(BookingPolicy(roomType))
    }
}