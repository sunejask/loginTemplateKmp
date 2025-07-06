package com.example.logintemplate.components.ui

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.logintemplate.ui.components.isValidPassword
import kotlin.test.Test

class IsValidPasswordTest {
    @Test
    fun validPassword() {
        assertThat(isValidPassword("test4A"))
            .isEqualTo(true)
    }

    @Test
    fun invalidPasswords() {
        assertThat(isValidPassword("")).isEqualTo(false)
        assertThat(isValidPassword("test")).isEqualTo(false)
        assertThat(isValidPassword("test4")).isEqualTo(false)
    }
}