package hotel_service.hotel

import hotel_service.hotel.room.RoomNumber
import hotel_service.hotel.room.RoomType

data class Hotel(val hotelId: HotelId, val hotelName: HotelName) {
    private val rooms = mutableMapOf<RoomNumber, RoomType>()

    fun setupRoom(roomNumber: RoomNumber, roomType: RoomType): Unit
    {
        this.rooms[roomNumber] = roomType
    }

    fun hasThisTypeOfRoom(roomType: RoomType): Boolean = rooms.containsValue(roomType)
    fun rooms(): Int= this.rooms.size
    fun roomNumberAvailableForType(roomType: RoomType, notAvailableRoomNumbers: Set<RoomNumber> = emptySet()): RoomNumber? {
        return this.rooms.filter { !notAvailableRoomNumbers.contains(it.key) && it.value == roomType }.keys.toList().firstOrNull()
    }
    fun roomTypeByRoomNumber(roomNumber: RoomNumber): RoomType? = rooms[roomNumber]
    fun clearRooms() = this.rooms.clear()
}