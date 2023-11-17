package com.anne.play.ui.view.edit

import java.util.regex.Pattern

/**
 *
 * Author:AnneLo
 * Time:2023/10/4
 */
private const val EMAIL_VALIDATION_REGEX = "^[A-Za-z0-9]{6,}"

class EmailState : TextFieldState(validator = ::isEmailValid, errorFor = ::emailValidationError)

private fun emailValidationError(email: String): String {
    return "error:$email"
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.matches(EMAIL_VALIDATION_REGEX, email)
}
