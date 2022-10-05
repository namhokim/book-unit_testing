package com.example.book.crm.repository

import com.example.book.crm.User
import com.example.book.crm.UserType

class Database {
    companion object {
        private val users: MutableMap<Int, UserDto> = mutableMapOf(
            12345 to UserDto(
                email = "namo@example.com",
                type = UserType.Employee,
            )
        )
        private var numberOfEmployees = users.size

        fun getUserById(userId: Int): UserDto {
            return users[userId] ?: throw IllegalArgumentException()
        }

        fun getCompany() = CompanyDto(
            companyDomainName = "example.com",
            numberOfEmployees = numberOfEmployees,
        )

        fun saveCompany(newNumber: Int) {
            numberOfEmployees += newNumber
        }

        fun saveUser(user: User) {
            users[user.userId] = UserDto(
                email = user.email,
                type = user.type,
            )
        }
    }
}
