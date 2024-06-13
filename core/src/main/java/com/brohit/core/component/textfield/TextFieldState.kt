package com.brohit.core.component.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

data class InputDate<T>(val text: String, val data: T)
class DataTextFieldState<T>(
    initialData: InputDate<T>? = null,
    validator: (String) -> Boolean = { true },
    errorFor: (String) -> String = { "" }
) : TextFieldState(validator, errorFor) {
    init {
        initialData?.text?.let {
            text = it
        }
    }


    var data: T? by mutableStateOf(initialData?.data)
        private set

    fun updateData(data: T, text: String) {
        this.data = data
        this.text = text
    }
}


open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val errorFor: (String) -> String = { "" },
) {
    var text: String by mutableStateOf("")

    // was the TextField ever focused
    var isFocusedDirty: Boolean by mutableStateOf(false)
    var isFocused: Boolean by mutableStateOf(false)
    private var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        // only show errors if the text was at least once focused
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun reset() {
        text = ""
        isFocused = false
        isFocusedDirty = false
        displayErrors = false
    }

    fun showErrors() = !isValid && displayErrors

    open fun getError(): String? {
        return if (showErrors()) {
            errorFor(text)
        } else {
            null
        }
    }
}

fun textFieldStateSaver(state: TextFieldState) = listSaver<TextFieldState, Any>(
    save = { listOf(it.text, it.isFocusedDirty) },
    restore = {
        state.apply {
            text = it[0] as String
            isFocusedDirty = it[1] as Boolean
        }
    }
)

val TextFileStateSaver = textFieldStateSaver(TextFieldState())

@Composable
fun rememberTextFieldState(
    validator: (String) -> Boolean = { true },
    errorFor: (String) -> String = { "" },
): State<TextFieldState> {
    return rememberSaveable(stateSaver = TextFileStateSaver) {
        mutableStateOf(TextFieldState(validator, errorFor))
    }
}