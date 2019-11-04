package booking_service

import booking_policy_service.BookingPolicyService
import company_service.employee.EmployeeId
import company_service.employee.EmployeeRepository
import hotel_service.HotelService
import hotel_service.hotel.HotelId
import hotel_service.hotel.room.RoomType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class BookingService(
    private val employeeRepository: EmployeeRepository,
    private val hotelService: HotelService,
    private val bookingPolicyService: BookingPolicyService,
    private val bookingRepository: BookingRepository
) {
    fun book(employeeId: EmployeeId, hotelId: HotelId, roomType: RoomType, checkIn: LocalDate, checkOut: LocalDate): Booking {
        val employee = employeeRepository.findById(employeeId) ?: throw EmployeeIdNotRegisteredException(employeeId)
        val hotel = hotelService.findHotelBy(hotelId) ?: throw HotelIdNotRegisteredException(hotelId)

        if (!hotel.hasThisTypeOfRoom(roomType)) {
            throw HotelDoesNotHaveThisKindOfRoom(roomType)
        }

        if (!bookingPolicyService.isBookingAllowed(employeeId, roomType)) {
            throw BookingIsNotAllowedForThisTypeOfRoomFromThisEmployee(employeeId)
        }

        if (!(checkIn.isBefore(checkOut) && ChronoUnit.DAYS.between(checkIn, checkOut) >= 1)) {
            throw CheckOutNotOneDayAfterCheckInException()
        }

        val availableRoomNumber = hotel.roomNumberAvailableForType(roomType, bookingRepository.bookedRoomNumberByHotel(hotelId, checkIn, checkOut)) ?: throw NoRoomsAvailableOfThisTypeForHotel(hotelId, roomType)

        val booking = Booking(employeeId, hotelId, availableRoomNumber, checkIn, checkOut)

        bookingRepository.save(booking)

        return booking
    }

}