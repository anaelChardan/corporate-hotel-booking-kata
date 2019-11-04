package acceptance.context

import booking_policy_service.BookingPolicyService
import booking_service.BookingRepository
import company_service.company.CompanyRepository
import company_service.employee.EmployeeRepository
import cucumber.api.Scenario
import cucumber.api.java8.En
import hotel_service.hotel.HotelRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class ResetFixtures: En, KoinComponent {
    private val hotelRepository by inject<HotelRepository>()
    private val companyRepository by inject<CompanyRepository>()
    private val employeeRepository by inject<EmployeeRepository>()
    private val bookingRepository by inject<BookingRepository>()

    init {
        After { _: Scenario? ->
            hotelRepository.deleteAll()
            companyRepository.deleteAll()
            employeeRepository.deleteAll()
            bookingRepository.deleteAll()
        }
    }
}