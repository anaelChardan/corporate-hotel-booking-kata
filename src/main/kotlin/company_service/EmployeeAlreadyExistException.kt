package company_service

import company_service.employee.EmployeeId

class EmployeeAlreadyExistException(val id: EmployeeId): Throwable()