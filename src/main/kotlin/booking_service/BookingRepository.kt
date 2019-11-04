package booking_service

import company_service.employee.EmployeeId
import hotel_service.hotel.HotelId
import hotel_service.hotel.room.RoomNumber
import java.time.LocalDate

class BookingRepository {
    private var bookings: MutableMap<HotelId, MutableSet<Booking>> = mutableMapOf()

    public fun bookedRoomNumberByHotel(hotelId: HotelId, fromDate: LocalDate, toDate: LocalDate): Set<RoomNumber> {
        return bookings
            .getOrElse(hotelId, { emptySet<Booking>() })
            .filter { !(it.checkOut <= fromDate || it.checkIn >= toDate) }
            .map { it.roomNumber }.toSet()
    }

    fun save(booking: Booking) {
        bookings.putIfAbsent(booking.hotelId, mutableSetOf())
        bookings[booking.hotelId]!!.add(booking)
    }

    fun removeAllFromEmployee(employeeId: EmployeeId) {
        bookings = bookings.map { it.key to it.value.filter { it.employeeId != employeeId }.toMutableSet() }.toMap().toMutableMap()
    }

    fun countByEmployee(employeeId: EmployeeId): Int {
        return bookings.values.fold(0, { sum: Int, bookings: Set<Booking> -> sum + bookings.count { it.employeeId == employeeId }  })
    }

    fun deleteAll() {
        bookings.clear()
    }
}
