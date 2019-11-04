package acceptance.context

import cucumber.api.java8.En

// the purpose of this class is just to test the Cucumber task ./gradlew acceptance
class HelloContext : En {
    init {
        Given("I wrote this dummy feature file") {
            // nothing to do here for this dummy step
            // this is just to test the cucumber task
        }

        When("I launch the Cucumber task of the Gradle file") {
            // nothing to do here for this dummy step
            // this is just to test the cucumber task
        }

        Then("the scenario {string} passed") { string: String ->
            // nothing to do here for this dummy step
            // this is just to test the cucumber task
        }
    }
}
