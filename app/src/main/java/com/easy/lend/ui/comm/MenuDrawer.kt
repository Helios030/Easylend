package com.easy.lend.ui.comm

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easy.lend.R
import com.easy.lend.ext.show
import com.easy.lend.ui.theme.TextStyles.itemSelected
import com.easy.lend.ui.theme.colorPrimary


/**
 * @Author Ben
 * @Date 2022/11/4 15:35
 * @desc:
 */


@Composable
fun MenuDrawer(drawerState: DrawerState) {

    ModalDrawer(
        drawerState = drawerState,
        drawerShape = MaterialTheme.shapes.small,
        drawerContent = {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DrawerHeader()

                DrawerItem(
                    painter = painterResource(id = R.mipmap.icon_sign_query),
                    label = "help center",
                    onClick = {
                        "help center".show()
                    }
                )

                DrawerItem(
                    painter = painterResource(id = R.mipmap.icon_version),
                    label = "Version",
                    onClick = {
                        " Version ".show()
                    }
                )

                DrawerItem(
                    painter = painterResource(id = R.mipmap.icon_exit),
                    label = "Exit login",
                    onClick = {
                        "Exit login".show()
                    }
                )


            }

        },
        content = {


        }
    )
}


@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.icon_profile),
           null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id = R.string.app_name), style = itemSelected)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "hello,+92 8888952701",style = itemSelected)
    }
}


/**
 * 抽屉的Item
 */
@Composable
private fun DrawerItem(
    label: String,
    painter: Painter,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.Unspecified

        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
        )
    }
}


