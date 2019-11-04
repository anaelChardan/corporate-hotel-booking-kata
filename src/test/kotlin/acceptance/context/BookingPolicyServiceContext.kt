package acceptance.context

import booking_policy_service.BookingPolicyService
import company_service.EmployeeNotRegisteredException
import company_service.company.CompanyId
import company_service.employee.EmployeeId
import cucumber.api.java8.En
import hotel_service.hotel.room.RoomType
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.shouldThrow
import org.koin.core.KoinComponent
import org.koin.core.inject

class BookingPolicyServiceContext: En, KoinComponent {
    private val bookingPolicyService by inject<BookingPolicyService>()

    init {
        When("I add a booking policy for type {string} for company {string}") { roomTypes: String, companyId: String ->
            bookingPolicyService.setCompanyPolicy(CompanyId(companyId), roomTypes.toRoomTypes());
        }

        When("I add a booking policy for type {string} for employee {string}") { roomTypes: String, employeeId: String ->
            bookingPolicyService.setEmployeePolicy(EmployeeId(employeeId), roomTypes.toRoomTypes());
        }

        Then("the employee {string} should be able to book a type {string}") { employeeId: String, roomType: String ->
            bookingPolicyService.isBookingAllowed(EmployeeId(employeeId), RoomType(roomType)).shouldBeTrue()
        }

        Then("the employee {string} should not be able to book a type {string}") { employeeId: String, roomType: String ->
            bookingPolicyService.isBookingAllowed(EmployeeId(employeeId), RoomType(roomType)).shouldBeFalse()
        }

        Then("I cannot add a booking policy for type {string} for employee {string}") { roomTypes: String, employeeId: String ->
            shouldThrow<EmployeeNotRegisteredException> {
                bookingPolicyService.setEmployeePolicy(
                    EmployeeId(employeeId),
                    roomTypes.toRoomTypes()
                );
            }
        }

        Then("I cannot add a booking policy for type {string} for company {string}") { roomTypes: String, companyId: String ->
            shouldThrow<EmployeeNotRegisteredException> {
                bookingPolicyService.setCompanyPolicy(
                    CompanyId(companyId),
                    roomTypes.toRoomTypes()
                );
            }
        }
    }
}

fun String.toRoomTypes(): Set<RoomType> = this.split(",").map { RoomType(it) }.toSet()