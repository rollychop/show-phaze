package com.brohit.core.component.textfield


class NameState(val name: String? = null) :
    TextFieldState(validator = ::isNameValid, errorFor = ::emailValidationError) {
    init {
        name?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */

@Suppress("UNUSED_PARAMETER")
private fun emailValidationError(email: String): String {
    return "Name is too short"
}

private fun isNameValid(name: String): Boolean {
    return name.length > 5
}

val NameStateSaver = textFieldStateSaver(NameState())