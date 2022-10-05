package com.example.book.crm.repository

import com.example.book.crm.UserType

data class UserDto(
    val email: String,
    val type: UserType,
)
