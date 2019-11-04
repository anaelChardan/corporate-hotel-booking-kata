package booking_service

import hotel_service.hotel.HotelId
import hotel_service.hotel.room.RoomType

class NoRoomsAvailableOfThisTypeForHotel(hotelId: HotelId, roomType: RoomType) : BookingNotPossibleException("No room of type ${roomType} available in the hotel with id ${hotelId}")
