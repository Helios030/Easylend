package com.easy.lend.ui.comm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.easy.lend.ui.theme.ButtonStyles
import com.easy.lend.ui.theme.TextStyles
import com.easy.lend.ui.theme.color59E9B969
import com.easy.lend.ui.theme.shadow
import com.idsix.composewar.ui.common.intervalClick

interface PopupItem {
    fun isEnabled(): Boolean

    @Composable
    fun content(): String
}

@Composable
fun <T : PopupItem> ListPopup(
    list: List<T>,
    selectedIndex: Int,
    onSelectIndex: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    ListPopup(
        list = list,
        selectedIndex = selectedIndex,
        onSelectIndex = onSelectIndex,
        onDismissRequest = onDismissRequest,
        calculateEnabled = { isEnabled() },
        getContent = { content() },
    )
}

@Composable
fun <T> ListPopup(
    list: List<T>,
    selectedIndex: Int,
    onSelectIndex: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    calculateEnabled: T.() -> Boolean,
    getContent: @Composable T.() -> String,
) {
    Popup(
        alignment = Alignment.TopCenter,
        offset = IntOffset(
            x = 0, y = 64
        ),
        onDismissRequest = onDismissRequest,
    ) {
        LazyColumn(
            modifier = Modifier
                .width(220.dp)
                .heightIn(min = 0.dp, max = 163.dp)
                .shadow(
                    color = color59E9B969,
                    shadowRadius = 8.dp
                )
        ) {
            itemsIndexed(list) { index, item ->
                val isEnabled = item.calculateEnabled()
                val isSelected = selectedIndex == index
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            brush =
                            if (isSelected) ButtonStyles.solidGradientBrush
                            else ButtonStyles.transparentBrush
                        )
                        .intervalClick { onSelectIndex(index) }
                ) {
                    Text(
                        text = item.getContent(),
                        style =
                        when {
                            !isEnabled -> TextStyles.itemUnEnabled
                            isSelected -> TextStyles.itemSelected
                            else -> TextStyles.itemNormal
                        },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}