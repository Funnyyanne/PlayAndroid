package com.anne.play.ui.view.edit

import java.util.regex.Pattern

/**
 *
 * Author:AnneLo
 * Time:2023/11/27
 */
private const val PASSWORD_VALIDATION_REGEX = "^[A-Za-z0-9]{6,}"

class PasswordState :
    TextFieldState(validator = ::isPasswordValid, errorFor = ::passwordValidationError)

fun isPasswordValid(password: String): Boolean {
    return Pattern.matches(PASSWORD_VALIDATION_REGEX, password)
}

@Suppress("UNUSED_PARAMETER")
private fun passwordValidationError(password: String): String {
    return "error"
}
