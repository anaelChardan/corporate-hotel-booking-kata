package booking_service

import hotel_service.hotel.room.RoomType

class HotelDoesNotHaveThisKindOfRoom(roomType: RoomType) : BookingNotPossibleException("The hotel does not have room of kind ${roomType}")
