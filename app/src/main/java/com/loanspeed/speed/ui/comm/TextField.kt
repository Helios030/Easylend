package com.loanspeed.speed.ui.comm

import android.annotation.SuppressLint
import android.text.InputFilter
import android.text.SpannedString
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.repository.bean.ChooseItem
import com.loanspeed.speed.ui.theme.TextStyles
import com.loanspeed.speed.ui.theme.colorPrimary
import com.loanspeed.speed.ui.common.intervalClick

val emptySpannedString = SpannedString("")

object KeyboardOptions {
    val Number = KeyboardOptions(keyboardType = KeyboardType.Number)
}

object InputFilters {
    val Int.maxLength: InputFilter
        get() {
            return InputFilter.LengthFilter(this)
        }
    val Number = InputFilter { source, _, _, _, _, _ ->
        source?.filter { it.isDigit() } ?: ""
    }
    val amount = InputFilter { source, _, _, _, _, _ ->
        source?.filter { it.isDigit() || it == '.' } ?: ""
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun PlaceholderTextFiled(
    boxModifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() },
    inputFilters: List<InputFilter> = listOf(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    readOnly: Boolean = false,
    enabled: Boolean = true
) {
    Box(
        modifier = boxModifier.height(50.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        val isInput by remember(value) { mutableStateOf(value.isNotEmpty()) }
        BasicTextField(
            value = value,
            onValueChange = { before ->
                var newValue = before
                inputFilters.forEach {
                    newValue = it.filter(newValue, 0, newValue.length, emptySpannedString, 0, 0)
                        ?.toString() ?: newValue
                }
                onValueChange(newValue)
            },
            modifier = Modifier
                .focusRequester(focusRequester = focusRequester)
                .fillMaxWidth(),
            textStyle = TextStyles.fieldValue,
            singleLine = true,
            maxLines = 1,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            cursorBrush = SolidColor(colorPrimary),
            readOnly = readOnly,
            enabled = enabled
        )
        if (!isInput) {
            Text(
                text = placeholder,
                style = TextStyles.fieldPlaceholder,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp),
            )
        }
    }
}

@SuppressLint("ModifierParameter")
@Composable
fun <T : ChooseItem> PlaceholderTextSelector(
    boxModifier: Modifier = Modifier.fillMaxWidth(),
    value: T?,
    placeholder: String,
    onClick: () -> Unit
) {
    Box(
        modifier = boxModifier
            .height(50.dp)
            .intervalClick(onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = value?.getValue() ?: "",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyles.fieldValue,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (value == null || value.isEmpty()) {
            Text(
                text = placeholder,
                style = TextStyles.fieldPlaceholder,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp),
            )
        }

    }


    Icon(
        Icons.Filled.ExpandMore, contentDescription = null,
        modifier = Modifier.size(22.dp)
    )


}