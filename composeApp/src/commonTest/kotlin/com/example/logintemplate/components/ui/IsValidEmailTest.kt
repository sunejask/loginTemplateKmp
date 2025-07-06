package com.example.logintemplate.components.ui

import com.example.logintemplate.ui.components.isValidEmail

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class IsValidEmailTest {
    @Test
    fun validEmail() {
        assertThat(isValidEmail("test@test.pl"))
            .isEqualTo(true)
    }

    @Test
    fun invalidEmails() {
        assertThat(isValidEmail("")).isEqualTo(false)
        assertThat(isValidEmail("testexample.com")).isEqualTo(false)
        assertThat(isValidEmail("test@com")).isEqualTo(false)
    }
}