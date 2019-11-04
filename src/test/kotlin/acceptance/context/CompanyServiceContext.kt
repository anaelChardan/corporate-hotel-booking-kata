package acceptance.context

import company_service.company.CompanyId
import company_service.CompanyService
import company_service.EmployeeAlreadyExistException
import company_service.employee.EmployeeId
import cucumber.api.java8.En
import io.kotlintest.shouldThrow
import org.koin.core.KoinComponent
import org.koin.core.inject

class CompanyServiceContext: En, KoinComponent {
    private val companyService by inject<CompanyService>()

    init {
        Given("nothing") {}

        Given("a company {string} with the employee {string}") { companyId: String, employeeId: String ->
            companyService.addEmployee(CompanyId(companyId), EmployeeId(employeeId))
        }

        When("I add the employee {string} to the company {string}") { employeeId: String, companyId: String ->
            companyService.addEmployee(CompanyId(companyId), EmployeeId(employeeId))
        }

        When("I delete the employee {string}") { employeeId: String ->
            companyService.deleteEmployee(EmployeeId(employeeId))
        }

        Then("I should be able to add the employee {string} to the company {string}") { employeeId: String, companyId: String ->
            companyService.addEmployee(CompanyId(companyId), EmployeeId(employeeId))
        }

        Then("I should not be able to add the employee {string} to the company {string}") { employeeId: String, companyId: String ->
            shouldThrow<EmployeeAlreadyExistException> {
                companyService.addEmployee(CompanyId(companyId), EmployeeId(employeeId))
            }
        }

        Then("I should be able to delete the employee {string}") { employeeId: String ->
            companyService.deleteEmployee(EmployeeId(employeeId))
        }
    }
}