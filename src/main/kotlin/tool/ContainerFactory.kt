package tool

import booking_policy_service.BookingPolicyService
import booking_service.BookingRepository
import booking_service.BookingService
import company_service.company.CompanyRepository
import company_service.CompanyService
import company_service.employee.EmployeeRepository
import hotel_service.hotel.HotelRepository
import hotel_service.HotelService
import org.koin.dsl.module
import org.koin.core.module.Module

class ContainerFactory {
    fun create(): List<Module> {
        return listOf(module {
            single { EmployeeRepository() }
            single { HotelRepository() }
            single { BookingRepository() }
            single { CompanyRepository() }
            single { HotelService(get()) }
            single { BookingService(get(), get(), get(), get())}
            single { CompanyService(get(), get(), get()) }
            single { BookingPolicyService(get(), get()) }
        })
    }
}
