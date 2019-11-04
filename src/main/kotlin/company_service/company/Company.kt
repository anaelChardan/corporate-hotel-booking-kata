package company_service.company

import booking_policy_service.BookingPolicy

data class Company(val companyId: CompanyId) {
    private val policies: MutableSet<BookingPolicy> = mutableSetOf()

    fun addPolicies(bookingPolicies: Set<BookingPolicy>) = this.policies.addAll(bookingPolicies)
    fun policies() = policies
}