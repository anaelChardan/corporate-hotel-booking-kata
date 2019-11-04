Feature:
    In order to have a good booking service
    For the user of this application
    We need to setup a nice company service

Scenario: It is possible to add a new employee
    Given nothing
    Then I should be able to add the employee "12" to the company "42"

Scenario: It is possible to delete a new employee
    Given nothing
    When I add the employee "12" to the company "42"
    Then I should be able to delete the employee "12"

Scenario: The deletion of an employee deletes all bookings related
    Given a company "1234" with the employee "12"
    And an hotel with an id "42" and a name "Hilton"
    And I set the type "STANDARD" for the room 1 to the hotel "42"
    When I add a booking policy for type "STANDARD" for company "1234"
    And I book a "STANDARD" room from employee "12" for hotel id "42" from "2019-12-09" to "2019-12-10"
    Then the number of bookings from the employee "12" should be equal to 1
    When I delete the employee "12"
    Then the number of bookings from the employee "12" should be equal to 0

Scenario: It possible to add an existing employee after having removed it
    Given nothing
    When I add the employee "12" to the company "42"
    And I delete the employee "12"
    Then I should be able to add the employee "12" to the company "42"

