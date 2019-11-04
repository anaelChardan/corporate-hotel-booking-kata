package company_service.employee

import company_service.EmployeeNotRegisteredException
import company_service.employee.Employee
import company_service.employee.EmployeeId
import tool.getOrThrow

class EmployeeRepository {
    private val employees: MutableMap<EmployeeId, Employee> = mutableMapOf()

    fun add(employee: Employee) = this.employees.put(employee.employeeId, employee)
    fun findById(employeeId: EmployeeId) = this.employees[employeeId]
    fun getById(employeeId: EmployeeId) = this.employees.getOrThrow(employeeId, EmployeeNotRegisteredException())
    fun removeById(employeeId: EmployeeId) = this.employees.remove(employeeId)
    fun deleteAll() = employees.clear()
}