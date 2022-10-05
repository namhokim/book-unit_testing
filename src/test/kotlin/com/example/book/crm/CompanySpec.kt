package com.example.book.crm

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class CompanySpec : FunSpec({

    test("Differentiates a corporate email from non corporate") {
        table(
            headers("domain", "email", "expectedResult"),
            row("mycorp.com", "email@mycorp.com", true),
            row("mycorp.com", "email@gmail.com", false),
        ).forAll { domain, email, expectedResult ->
            val sut = Company(companyDomainName = domain, numberOfCompanyEmployees = 0)

            val isEmailCorporate = sut.isEmailCorporate(email)

            isEmailCorporate shouldBe expectedResult
        }
    }

})
