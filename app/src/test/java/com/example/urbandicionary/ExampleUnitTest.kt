package com.example.urbandicionary

import com.example.urbandicionary.utils.CheckInput
import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Assert.*


class UtilsTest {

    @Test
    fun validateValidInput(){

        val input= "home"
        assertThat(
            CheckInput.isValidInput(input),
            CoreMatchers.`is`(true)
        )
    }

    @Test
    fun validateNotValidInput(){

        val input= "home-123"
        assertThat(
            CheckInput.isValidInput(input),
            CoreMatchers.`is`(false)
        )
    }
}

