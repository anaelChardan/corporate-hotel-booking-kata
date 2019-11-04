Feature:
    In order to have a good booking service
    For the user of this application
    We need to setup a nice hotel service

Scenario: It is possible to add a new hotel given an id
    Given an Hotel ID "1234" and an hotel Name "Hilton"
    When I add the Hotel to the Hotel Service
    Then I should be able to find it through its id

Scenario: It is not possible to add an hotel twice
    Given an Hotel ID "1234" and an hotel Name "Hilton"
    When I add the Hotel to the Hotel Service
    Then It should throw an exception if I enter the same hotel twice

Scenario: It is possible to add a room to an hotel
    Given an hotel with an id "1234" and a name "Hilton"
    When I set the type "QUEEN" for the room 12 to the hotel "1234"
    And I set the type "KING" for the room 13 to the hotel "1234"
    Then the Hotel "1234" should have 2 rooms

Scenario: It is not possible to add a room to a non existent hotel
    Given no hotel
    Then It should throw an exception if try to set room to an nonexistent hotel

Scenario: It is possible to update a room to an hotel
    Given an hotel with an id "1234" and a name "Hilton"
    When I set the type "QUEEN" for the room 12 to the hotel "1234"
    And I set the type "KING" for the room 12 to the hotel "1234"
    Then the room 12 of the hotel "1234" should be "KING"
