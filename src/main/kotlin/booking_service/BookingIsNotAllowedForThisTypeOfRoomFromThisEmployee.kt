package booking_service

import company_service.employee.EmployeeId

class BookingIsNotAllowedForThisTypeOfRoomFromThisEmployee(employeeId: EmployeeId) : BookingNotPossibleException("The booking is not possible due to ${employeeId} booking policy")
