package hotel_service

import hotel_service.hotel.HotelId

class HotelIdNotRegisteredException(val hotelId: HotelId) : Throwable(message = "The hotel id ${hotelId.id} has not been registered")
