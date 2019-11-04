package booking_service

import company_service.employee.EmployeeId
import hotel_service.hotel.HotelId
import hotel_service.hotel.room.RoomNumber
import java.time.LocalDate

data class Booking(
    val employeeId: EmployeeId,
    val hotelId: HotelId,
    val roomNumber: RoomNumber,
    val checkIn: LocalDate,
    val checkOut: LocalDate
)