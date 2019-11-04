package hotel_service

import hotel_service.hotel.HotelId

class HotelIdAlreadyRegisteredException(val hotelId: HotelId) : Throwable(message = "The hotel id ${hotelId.id} has already been registered")
