package company_service

import booking_service.BookingRepository
import company_service.company.Company
import company_service.company.CompanyId
import company_service.company.CompanyRepository
import company_service.employee.Employee
import company_service.employee.EmployeeId
import company_service.employee.EmployeeRepository

class CompanyService(
    private val companyRepository: CompanyRepository,
    private val employeeRepository: EmployeeRepository,
    private val bookingRepository: BookingRepository
) {
    fun addEmployee(companyId: CompanyId, employeeId: EmployeeId) {
        if (null !== employeeRepository.findById(employeeId)) {
            throw EmployeeAlreadyExistException(employeeId)
        }

        companyRepository.addIfNotExists(Company(companyId))
        employeeRepository.add(Employee(employeeId, companyId))
    }

    fun deleteEmployee(employeeId: EmployeeId) {
        employeeRepository.removeById(employeeId)
        bookingRepository.removeAllFromEmployee(employeeId)
    }
}