package com.example.book.crm

class Company(
    private val companyDomainName: String,
    numberOfCompanyEmployees: Int,
) {
    var numberOfEmployees: Int = numberOfCompanyEmployees
        private set

    fun changeNumberOfEmployees(delta: Int) {
        numberOfEmployees += delta
    }

    fun isEmailCorporate(email: String): Boolean {
        val emailDomain: String = email.split('@')[1]
        return emailDomain == companyDomainName
    }
}
