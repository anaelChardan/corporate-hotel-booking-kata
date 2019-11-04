package acceptance.context

import booking_service.BookingNotPossibleException
import booking_service.BookingRepository
import booking_service.BookingService
import company_service.employee.EmployeeId
import cucumber.api.java8.En
import hotel_service.hotel.HotelId
import hotel_service.hotel.room.RoomType
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.time.LocalDate

class BookingServiceContext: En, KoinComponent {
    private val bookingService by inject<BookingService>()
    private val bookingRepository by inject<BookingRepository>()

    init {
        Then("I cannot book a {string} room from employee {string} for hotel id {string} from {string} to {string}") { roomType: String, employeeId: String, hotelId: String, checkIn: String, checkOut: String ->
            shouldThrow<BookingNotPossibleException> {
                bookingService.book(EmployeeId(employeeId), HotelId(hotelId), RoomType(roomType), LocalDate.parse(checkIn), LocalDate.parse(checkOut))
            }
        }

        Then("I can book a {string} room from employee {string} for hotel id {string} from {string} to {string}") { roomType: String, employeeId: String, hotelId: String, checkIn: String, checkOut: String ->
            bookingService.book(EmployeeId(employeeId), HotelId(hotelId), RoomType(roomType), LocalDate.parse(checkIn), LocalDate.parse(checkOut))
        }

        Then("the number of bookings from the employee {string} should be equal to {int}") { employeeId: String, numberOfBooking: Int ->
            bookingRepository.countByEmployee(EmployeeId(employeeId)).shouldBe(numberOfBooking)
        }

        When("I book a {string} room from employee {string} for hotel id {string} from {string} to {string}") { roomType: String, employeeId: String, hotelId: String, checkIn: String, checkOut: String ->
            bookingService.book(EmployeeId(employeeId), HotelId(hotelId), RoomType(roomType), LocalDate.parse(checkIn), LocalDate.parse(checkOut))
        }
    }
}