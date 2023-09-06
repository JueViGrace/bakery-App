package com.bakery.bakeryapp.ui.presentation.app.screens.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.ui.presentation.app.components.HeadingTextComponent
import com.bakery.bakeryapp.ui.presentation.app.navigation.AppRouter
import com.bakery.bakeryapp.ui.presentation.app.navigation.Screen
import com.bakery.bakeryapp.ui.presentation.app.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SingUpScreen)
    }
}