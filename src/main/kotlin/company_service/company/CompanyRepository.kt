package company_service.company

import company_service.CompanyNotRegisteredException
import tool.getOrThrow

class CompanyRepository {
    private val companies: MutableMap<CompanyId, Company> = mutableMapOf()

    fun addIfNotExists(company: Company) = companies.putIfAbsent(company.companyId, company)
    fun getById(companyId: CompanyId) = this.companies.getOrThrow(companyId, CompanyNotRegisteredException())
    fun deleteAll() = companies.clear()
}