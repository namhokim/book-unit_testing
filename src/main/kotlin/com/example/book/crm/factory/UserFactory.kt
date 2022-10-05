package com.example.book.crm.factory

import com.example.book.crm.User
import com.example.book.crm.repository.UserDto

class UserFactory {
    companion object {
        fun create(userId: Int, data: UserDto): User {
            return User(
                userId = userId,
                email = data.email,
                type = data.type,
                isEmailConfirmed = false,
            )
        }
    }
}
