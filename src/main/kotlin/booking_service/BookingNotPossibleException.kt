package booking_service

open class BookingNotPossibleException(val why: String): Throwable(why)