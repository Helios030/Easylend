package com.easy.lend.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.easy.lend.R
import com.easy.lend.rout.RoutPath
import com.easy.lend.util.SpRepository

@Composable
fun SplashScreen(navController: NavController, vm: SplashViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.app_icon),
            null,
            modifier = Modifier.size(150.dp),
        )
        if (vm.isFinish) {
            if (SpRepository.token.isEmpty()) {
                navController.navigate(RoutPath.LOGIN)
            } else {
                navController.navigate(RoutPath.INDEX)
            }


        }
    }


}