package com.example.yummy.unittest

object RegistrationUtil {
    private val existingUser = listOf<String>("Peter", "Carl")

    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        return true
    }
}