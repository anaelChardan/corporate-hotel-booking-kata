package hotel_service

import hotel_service.hotel.Hotel
import hotel_service.hotel.HotelId
import hotel_service.hotel.HotelName
import hotel_service.hotel.HotelRepository
import hotel_service.hotel.room.RoomNumber
import hotel_service.hotel.room.RoomType

class HotelService(private val hotelRepository: HotelRepository) {
    fun addHotel(hotelId: HotelId, hotelName: HotelName) {
        if (null != findHotelBy(hotelId)) {
            throw HotelIdAlreadyRegisteredException(hotelId)
        }

        hotelRepository.upsert(Hotel(hotelId, hotelName))
    }

    fun setRoom(hotelId: HotelId, roomNumber: RoomNumber, roomType: RoomType) {
        if(null == findHotelBy(hotelId)) {
            throw HotelIdNotRegisteredException(hotelId)
        }

        this.findHotelBy(hotelId)!!.setupRoom(roomNumber, roomType)
    }

    fun findHotelBy(hotelId: HotelId): Hotel? = hotelRepository.findById(hotelId)
}