package com.bakery.bakeryapp.domain.rules

import android.util.Patterns

object Validator {

    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (fName.isNotEmpty() && fName.length >= 4)
        )
    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (lName.isNotEmpty() && lName.length >= 4)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun validateBirthDay(bDay: String): ValidationResult {
        return ValidationResult(
            (bDay.isNotEmpty())
        )
    }

    fun validatePhone(phone: String): ValidationResult {
        return ValidationResult(
            (phone.isNotEmpty() && phone.length > 10)
        )
    }

    fun validateFullName(fName: String): ValidationResult {
        return ValidationResult(
            (fName.isNotEmpty() && fName.length >= 6)
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length >= 6)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean): ValidationResult {
        return ValidationResult(
            statusValue
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)
