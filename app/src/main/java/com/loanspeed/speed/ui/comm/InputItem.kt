package com.loanspeed.speed.ui.comm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loanspeed.speed.ui.theme.colorLine
import com.loanspeed.speed.ui.theme.colorPrimary
import com.loanspeed.speed.ui.theme.colorWhiteF7

/**
 * @Author Ben
 * @Date 2023/2/1 17:07
 * @desc:
 */

@Composable
fun ItemBg(contact: @Composable () -> Unit) {
    Card(shape = RoundedCornerShape(15.dp), backgroundColor = colorWhiteF7, modifier = Modifier.padding(horizontal = 5.dp).fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {

          contact.invoke()
        }
    }
}


@Composable
fun InputItem(itemTitle: String, text: MutableState<String>, label: String) {


    ItemBg {
        Text(text = itemTitle, color = colorPrimary, modifier = Modifier.fillMaxWidth(0.4F))
        CustomEditHint(
            hint = label,
            text = text.value,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { text.value = it }, singleLine = true)
    }
    
 

}

@Preview
@Composable
fun showInputItem() {
    val text = remember { mutableStateOf("") }
    InputItem(itemTitle = "test", text = text, label = "input test")

}


/**
 * 只增加 hint;  适合多行输入框, 没有前后Icon的小输入框; 背景 尺寸等 定义在modifier中;
 * @param hint: 空字符时的提示
 */
@Composable
fun CustomEditHint(
    hint: String = "",
    text: String = "",
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    cursorBrush: Brush = SolidColor(MaterialTheme.colors.primary)
) {
    // 焦点, 用于控制是否显示 右侧叉号
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = singleLine,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions.Default,
        keyboardActions = keyboardActions,
        visualTransformation = visualTransformation,
        cursorBrush = cursorBrush,
        decorationBox = @Composable { innerTextField ->
            if (text.isEmpty())
                Text(text = hint, color = colorLine, style = textStyle)
            innerTextField()
        }
    )
}

