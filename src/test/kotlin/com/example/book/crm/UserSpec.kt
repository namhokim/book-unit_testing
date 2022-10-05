package com.example.book.crm

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class UserSpec : FunSpec({

    var company: Company? = null
    val getCompany: () -> Company = {
        company = Company(companyDomainName = "mycorp.com", numberOfCompanyEmployees = 1)
        company!!
    }

    test("Not confirmed email from non corporate to corporate") {
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer, isEmailConfirmed = true)

        val result: String? = sut.changeEmail(newEmail = "new@mycorp.com", getCompany = getCompany)

        sut.email shouldBe "user@gmail.com"
        sut.type shouldBe UserType.Customer
        result shouldBe "Can't change a confirmed email"
    }

    test("Changing email from non corporate to corporate") {
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer, isEmailConfirmed = false)

        sut.changeEmail(newEmail = "new@mycorp.com", getCompany = getCompany)

        company!!.numberOfEmployees shouldBe 2
        sut.email shouldBe "new@mycorp.com"
        sut.type shouldBe UserType.Employee
    }

    test("Changing email from corporate to non corporate") {
        val sut = User(userId = 1, email = "new@mycorp.com", type = UserType.Employee, isEmailConfirmed = false)

        sut.changeEmail(newEmail = "user@gmail.com", getCompany = getCompany)

        company!!.numberOfEmployees shouldBe 0
        sut.email shouldBe "user@gmail.com"
        sut.type shouldBe UserType.Customer
    }

    test("Changing email without changing user type") {
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer, isEmailConfirmed = false)

        sut.changeEmail(newEmail = "group@gmail.com", getCompany = getCompany)

        company!!.numberOfEmployees shouldBe 1
        sut.email shouldBe "group@gmail.com"
        sut.type shouldBe UserType.Customer
    }

    test("Changing email to the same one") {
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer, isEmailConfirmed = false)

        sut.changeEmail(newEmail = "user@gmail.com", getCompany = getCompany)

        company!!.numberOfEmployees shouldBe 1
        sut.email shouldBe "user@gmail.com"
        sut.type shouldBe UserType.Customer
    }

})
