Feature:
    In order to have a good booking service
    For the user of this application
    We need to setup a nice booking

Scenario: A booking is not possible if the checkout not at least one day after checking
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    When I add a booking policy for type "LUXE" for company "1234"
    Then I cannot book a "LUXE" room from employee "12" for hotel id "42" from "2019-12-10" to "2019-12-09"

Scenario: A booking is not possible if hotel does not exist
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    When I add a booking policy for type "LUXE" for company "1234"
    Then I cannot book a "LUXE" room from employee "12" for hotel id "46" from "2019-12-09" to "2019-12-10"

Scenario: A booking is not possible if employee does not exist
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    When I add a booking policy for type "LUXE" for company "1234"
    Then I cannot book a "LUXE" room from employee "25" for hotel id "46" from "2019-12-09" to "2019-12-10"

Scenario: A booking is not possible if hotel does not have this kind of room
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    When I add a booking policy for type "LUXE" for company "1234"
    Then I cannot book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-09" to "2019-12-10"

Scenario: A booking is not possible if the policy does not allow this kind of booking
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    And I set the type "LUXE" for the room 1 to the hotel "42"
    And I set the type "STANDARD" for the room 2 to the hotel "42"
    When I add a booking policy for type "LUXE" for company "1234"
    Then I cannot book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-09" to "2019-12-10"

Scenario: A booking is not possible if a booking overlap another one
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    And I set the type "STANDARD" for the room 1 to the hotel "42"
    When I add a booking policy for type "STANDARD" for company "1234"
    And I book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-09" to "2019-12-10"
    Then I cannot book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-08" to "2019-12-10"

Scenario: A booking is possible if it respects all conditions
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    And I set the type "STANDARD" for the room 1 to the hotel "42"
    When I add a booking policy for type "STANDARD" for company "1234"
    And I book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-09" to "2019-12-10"
    Then I can book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-08" to "2019-12-09"