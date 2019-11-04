package booking_service

class CheckOutNotOneDayAfterCheckInException: BookingNotPossibleException("The checkout day has to be after the check in")