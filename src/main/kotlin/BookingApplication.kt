import booking_policy_service.BookingPolicyService
import booking_service.BookingService
import company_service.CompanyService
import company_service.company.CompanyId
import company_service.employee.EmployeeId
import hotel_service.HotelService
import hotel_service.hotel.HotelId
import hotel_service.hotel.HotelName
import hotel_service.hotel.room.RoomNumber
import hotel_service.hotel.room.RoomType
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject
import tool.ContainerFactory
import java.time.LocalDate

class BookingApplication: KoinComponent {
    val bookingService by inject<BookingService>()
    val bookingPolicyService by inject<BookingPolicyService>()
    val companyService by inject<CompanyService>()
    val hotelService by inject<HotelService>()

    fun givenACompanyWithEmployee(companyId: String, employeeId: String) {
        companyService.addEmployee(CompanyId(companyId), EmployeeId(employeeId))
    }

    fun givenAnHotelWith(hotelId: String, hotelName: String) {
        hotelService.addHotel(HotelId(hotelId), HotelName(hotelName))
    }

    fun setTheTypeForRoomOfHotel(type: String, hotelId: String, roomNumber: Int) {
        hotelService.setRoom(HotelId(hotelId), RoomNumber(roomNumber), RoomType(type))
    }

    fun addThePolicyForCompany(roomTypes: String, companyId: String) {
        bookingPolicyService.setCompanyPolicy(CompanyId(companyId), roomTypes.toRoomTypes());
    }

    fun andIBook(employeeId: String, hotelId: String, roomType: String, checkIn: String, checkOut: String) {
        bookingService.book(EmployeeId(employeeId), HotelId(hotelId), RoomType(roomType), LocalDate.parse(checkIn), LocalDate.parse(checkOut))
    }

    fun String.toRoomTypes(): Set<RoomType> = this.split(",").map { RoomType(it) }.toSet()
}

fun main() {
    val regularServices = ContainerFactory().create()

    startKoin{ modules(regularServices) }

    val bookingApplication = BookingApplication()

    bookingApplication.givenACompanyWithEmployee("1234", "12")
    bookingApplication.givenAnHotelWith("42", "Hilton")
    bookingApplication.setTheTypeForRoomOfHotel("STANDARD", "42", 1)
    bookingApplication.addThePolicyForCompany("STANDARD", "1234")
    bookingApplication.andIBook("12", "42", "STANDARD", "2019-12-09", "2019-12-10")
    bookingApplication.andIBook("12", "42", "STANDARD", "2019-12-08", "2019-12-10")
}