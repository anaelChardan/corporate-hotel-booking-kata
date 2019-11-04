package company_service.employee

import booking_policy_service.BookingPolicy
import company_service.company.CompanyId

class Employee(val employeeId: EmployeeId, val companyId: CompanyId) {
    private val policies: MutableSet<BookingPolicy> = mutableSetOf()

    fun addPolicies(bookingPolicies: Set<BookingPolicy>) = this.policies.addAll(bookingPolicies)
    fun policies() = policies
}