package com.example.book.crm.factory

import com.example.book.crm.Company
import com.example.book.crm.repository.CompanyDto

class CompanyFactory {
    companion object {
        fun create(data: CompanyDto): Company {
            return Company(
                companyDomainName = data.companyDomainName,
                numberOfCompanyEmployees = data.numberOfEmployees,
            )
        }
    }
}
