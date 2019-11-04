package booking_service

import company_service.employee.EmployeeId

class EmployeeIdNotRegisteredException(val employeeId: EmployeeId) : BookingNotPossibleException("The employee with id ${employeeId} is not registered")
