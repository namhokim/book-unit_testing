package com.example.book.crm

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class UserSpec : FunSpec({

    test("Changing email from non corporate to corporate") {
        val company = Company(companyDomainName = "mycorp.com", numberOfCompanyEmployees = 1)
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer)

        sut.changeEmail(newEmail = "new@mycorp.com", company = company)

        company.numberOfEmployees shouldBe 2
        sut.email shouldBe "new@mycorp.com"
        sut.type shouldBe UserType.Employee
    }

    test("Changing email from corporate to non corporate") {
        val company = Company(companyDomainName = "mycorp.com", numberOfCompanyEmployees = 1)
        val sut = User(userId = 1, email = "new@mycorp.com", type = UserType.Employee)

        sut.changeEmail(newEmail = "user@gmail.com", company = company)

        company.numberOfEmployees shouldBe 0
        sut.email shouldBe "user@gmail.com"
        sut.type shouldBe UserType.Customer
    }

    test("Changing email without changing user type") {
        val company = Company(companyDomainName = "mycorp.com", numberOfCompanyEmployees = 1)
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer)

        sut.changeEmail(newEmail = "group@gmail.com", company = company)

        company.numberOfEmployees shouldBe 1
        sut.email shouldBe "group@gmail.com"
        sut.type shouldBe UserType.Customer
    }

    test("Changing email to the same one") {
        val company = Company(companyDomainName = "mycorp.com", numberOfCompanyEmployees = 1)
        val sut = User(userId = 1, email = "user@gmail.com", type = UserType.Customer)

        sut.changeEmail(newEmail = "user@gmail.com", company = company)

        company.numberOfEmployees shouldBe 1
        sut.email shouldBe "user@gmail.com"
        sut.type shouldBe UserType.Customer
    }

})
