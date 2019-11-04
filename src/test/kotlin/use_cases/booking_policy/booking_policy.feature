Feature:
    In order to have a good booking service
    For the user of this application
    We need to setup a nice booking_policy

Scenario: A booking is allowed if the company has the right booking policy
    Given a company "1234" with the employee "12"
    When I add a booking policy for type "LUXE" for company "1234"
    And I add a booking policy for type "STANDARD" for employee "12"
    Then the employee "12" should be able to book a type "LUXE"

Scenario: A booking is allowed if the employee has the right booking policy
    Given a company "1234" with the employee "12"
    When I add a booking policy for type "STANDARD" for company "1234"
    And I add a booking policy for type "LUXE" for employee "12"
    Then the employee "12" should be able to book a type "LUXE"

Scenario: A booking is allowed if neither employee nor company policies exist
    Given a company "1234" with the employee "12"
    Then the employee "12" should be able to book a type "LUXE"

Scenario: A booking is not allowed if neither employee nor company policies contains the right policy
    Given a company "1234" with the employee "12"
    When I add a booking policy for type "LUXE" for company "1234"
    And I add a booking policy for type "STANDARD" for employee "12"
    Then the employee "12" should not be able to book a type "DOUBLE"

Scenario: It cannot be used for a nonexistent employee
    Given a company "1234" with the employee "12"
    Then I cannot add a booking policy for type "LUXE" for employee "13"

Scenario: It cannot be used for a nonexistent company
    Given a company "1234" with the employee "12"
    Then I cannot add a booking policy for type "LUXE" for employee "13"
