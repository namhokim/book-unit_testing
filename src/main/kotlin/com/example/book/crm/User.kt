package com.example.book.crm

class User(
    val userId: Int,
    var email: String,
    var type: UserType,
    private val isEmailConfirmed: Boolean,
) {
    fun changeEmail(newEmail: String, getCompany: () -> Company): String? {
        if (isEmailConfirmed) {
            return "Can't change a confirmed email"
        }

        if (email == newEmail) {
            return null
        }

        val company: Company = getCompany()
        val isEmailCorporate: Boolean = company.isEmailCorporate(email = newEmail)
        val newType: UserType =
            if (isEmailCorporate) UserType.Employee
            else UserType.Customer

        if (type != newType) {
            val delta: Int =
                if (newType == UserType.Employee) 1
                else -1
            company.changeNumberOfEmployees(delta = delta)
        }

        this.email = newEmail
        this.type = newType

        return null
    }
}
