package hotel_service.hotel

class HotelRepository {
    private val hotels: MutableMap<HotelId, Hotel> = mutableMapOf();

    fun upsert(hotel: Hotel) = this.hotels.put(hotel.hotelId, hotel)
    fun findById(hotelId: HotelId): Hotel? = hotels[hotelId]

    fun deleteAll() {
        this.hotels.map { it.value.clearRooms() }
        this.hotels.clear()
    }
}