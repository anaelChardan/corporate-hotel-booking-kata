Feature:
    In order to discover the use cases of the Hotel service
    For developers of this project
    We will setup a Cucumber equivalent tool for Kotlin

Scenario: It runs a dummy feature file
    Given I wrote this dummy feature file
    When I launch the Cucumber task of the Gradle file
    Then the scenario "It runs a dummy feature file" passed
