package com.example.book.crm

import com.example.book.crm.bus.MessageBus
import com.example.book.crm.factory.UserFactory
import com.example.book.crm.repository.CompanyDto
import com.example.book.crm.repository.Database
import com.example.book.crm.repository.UserDto

class UserController(
    private val database: Database = Database(),
    private val messageBus: MessageBus = MessageBus(),
) {
    fun changeEmail(userId: Int, newEmail: String) {
        val userDto: UserDto = database.getUserById(userId = userId)
        val user: User = UserFactory.create(userId = userId, data = userDto)

        val companyDto: CompanyDto = database.getCompany()

        val newNumberOfEmployees: Int = user.changeEmail(
            newEmail = newEmail, companyDto.companyDomainName, companyDto.numberOfEmployees)

        database.saveCompany(newNumber = newNumberOfEmployees)
        database.saveUser(user = user)
        messageBus.sendEmailChangedMessage(userId = userId, newEmail = newEmail)
    }
}

fun main() {
    val controller = UserController()
    controller.changeEmail(userId = 12345, newEmail = "new@example.com")
    controller.changeEmail(userId = 12345, newEmail = "namo@example.com")
}
