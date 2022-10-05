package com.example.book.crm

import com.example.book.crm.bus.MessageBus
import com.example.book.crm.repository.CompanyDto
import com.example.book.crm.repository.Database
import com.example.book.crm.repository.UserDto

class User {
    var userId: Int = 0
        private set

    var email: String = ""
        private set

    var type: UserType = UserType.Employee
        private set


    fun changeEmail(userId: Int, newEmail: String) {
        val userDto: UserDto = Database.getUserById(userId = userId)
        this.userId = userId
        this.email = newEmail
        this.type = userDto.type
        if (userDto.email == newEmail) {
            return
        }

        val companyDto: CompanyDto = Database.getCompany()

        val emailDomain: String = newEmail.split('@')[1]
        val isEmailCorporate: Boolean = emailDomain == companyDto.companyDomainName
        val newType: UserType =
            if (isEmailCorporate) UserType.Employee
            else UserType.Customer

        if (userDto.type != newType) {
            val delta: Int =
                if (newType == UserType.Employee) 1
                else -1
            val newNumber: Int = companyDto.numberOfEmployees + delta
            Database.saveCompany(newNumber = newNumber)
        }

        this.email = newEmail
        this.type = newType

        Database.saveUser(this)
        MessageBus.sendEmailChangedMessage(userId, newEmail)
    }
}

fun main() {
    val user = User()
    user.changeEmail(userId = 12345, newEmail = "new@example.com")
    user.changeEmail(userId = 12345, newEmail = "namo@example.com")
}
