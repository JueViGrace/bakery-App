package com.bakery.bakeryapp.ui.presentation.app.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.ui.presentation.app.components.ButtonComponent
import com.bakery.bakeryapp.ui.presentation.app.components.ClickableLoginTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.DividerTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.EmailTextFieldComponent
import com.bakery.bakeryapp.ui.presentation.app.components.HeadingTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.NormalTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.PasswordTextFieldComponent
import com.bakery.bakeryapp.ui.presentation.app.components.UnderlinedTextComponent
import com.bakery.bakeryapp.ui.presentation.app.navigation.AppRouter
import com.bakery.bakeryapp.ui.presentation.app.navigation.Screen
import com.bakery.bakeryapp.ui.presentation.app.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.login))

            HeadingTextComponent(value = stringResource(id = R.string.welcome))

            Spacer(modifier = Modifier.height(80.dp))

            EmailTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.ic_email),
                onTextSelected = {
                    // loginViewModel.onEvent(UIEvent.EmailChanged(it))
                }
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    // loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            UnderlinedTextComponent(value = stringResource(R.string.forgot_password))

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(value = stringResource(id = R.string.login), onButtonClicked = {})

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                AppRouter.navigateTo(Screen.SingUpScreen)
            })
        }
    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SingUpScreen)
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen()
}
