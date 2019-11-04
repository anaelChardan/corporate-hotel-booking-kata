package booking_service

import hotel_service.hotel.HotelId

class HotelIdNotRegisteredException(val hotelId: HotelId) : BookingNotPossibleException("The hotel with id ${hotelId} is not registered")
