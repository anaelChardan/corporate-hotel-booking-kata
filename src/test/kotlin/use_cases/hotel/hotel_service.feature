Feature:
    In order to have a good booking service
    For the user of this application
    We need to setup a nice booking service

Scenario: It is possible to add a new hotel given an id
    Given an Hotel ID "1234" and an hotel Name "Hilton"
    When I add the Hotel to the Hotel Service
    Then I should be able to find it through its id
