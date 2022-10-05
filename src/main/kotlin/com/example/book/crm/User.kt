package com.example.book.crm

class User(
    val userId: Int,
    var email: String,
    var type: UserType
) {
    fun changeEmail(newEmail: String, companyDomainName: String, numberOfEmployees: Int): Int {
        if (email == newEmail) {
            return numberOfEmployees
        }

        val emailDomain: String = newEmail.split('@')[1]
        val isEmailCorporate: Boolean = emailDomain == companyDomainName
        val newType: UserType =
            if (isEmailCorporate) UserType.Employee
            else UserType.Customer

        this.email = newEmail
        this.type = newType

        return if (type != newType) {
            val delta: Int =
                if (newType == UserType.Employee) 1
                else -1
            numberOfEmployees + delta
        } else {
            numberOfEmployees
        }
    }
}
