package acceptance.context

import cucumber.api.java8.En
import hotel_service.*
import hotel_service.hotel.Hotel
import hotel_service.hotel.HotelId
import hotel_service.hotel.HotelName
import hotel_service.hotel.room.RoomNumber
import hotel_service.hotel.room.RoomType
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.koin.core.KoinComponent
import org.koin.core.inject


class HotelServiceContext: En, KoinComponent {
    private var hotelId: String? = null
    private var hotelName: String? = null
    private val hotelService by inject<HotelService>()


    init {
        Given("an Hotel ID {string} and an hotel Name {string}") { hotelID: String, hotelName: String ->
            this.hotelId = hotelID
            this.hotelName = hotelName
        }

        Given("no hotel") {}

        Given("an hotel with an id {string} and a name {string}") { hotelID: String, hotelName: String ->
            hotelService.addHotel(HotelId(hotelID), HotelName(hotelName))
        }

        When("I add the Hotel to the Hotel Service") {
            checkHotelProperties()
            hotelService.addHotel(HotelId(this.hotelId!!), HotelName(this.hotelName!!))
        }

        When("I set the type {string} for the room {int} to the hotel {string}") { type: String, roomNumber: Int, hotelId: String ->
            hotelService.setRoom(HotelId(hotelId), RoomNumber(roomNumber), RoomType(type))
        }

        Then("I should be able to find it through its id") {
            checkHotelProperties()
            hotelService.findHotelBy(HotelId(this.hotelId!!)).shouldBe(
                Hotel(
                    HotelId(this.hotelId!!),
                    HotelName(this.hotelName!!)
                )
            )
        }

        Then("the Hotel {string} should have {int} rooms") { hotelId: String, roomNumber: Int ->
            val hotel = hotelService.findHotelBy(HotelId(hotelId))
            if (null == hotel) {
                throw Exception("The hotel is null")
            } else {
                hotel.rooms().shouldBe(roomNumber)
            }
        }

        Then("the room {int} of the hotel {string} should be {string}") { roomNumber: Int, hotelId: String, roomType: String ->
            val hotel = hotelService.findHotelBy(HotelId(hotelId))
            if (null == hotel) {
                throw Exception("The hotel is null")
            } else {
                hotel.roomTypeByRoomNumber(RoomNumber(roomNumber)).shouldBe(RoomType(roomType))
            }
        }

        Then("It should throw an exception if I enter the same hotel twice") {
            checkHotelProperties()
            shouldThrow<HotelIdAlreadyRegisteredException> {
                hotelService.addHotel(HotelId(this.hotelId!!), HotelName(this.hotelName!!))
            }
        }

        Then("It should throw an exception if try to set room to an nonexistent hotel") {
            shouldThrow<HotelIdNotRegisteredException> {
                hotelService.setRoom(HotelId("A_NON_EXISTENT_ID"), RoomNumber(12), RoomType("LUXE"))
            }
        }
    }

    private fun checkHotelProperties() {
        if (this.hotelId == null) {
            throw Exception("Hotel Id hasn't been initialized")
        }
        if (this.hotelName == null) {
            throw Exception("Hotel name hasn't been initialized")
        }
    }
}