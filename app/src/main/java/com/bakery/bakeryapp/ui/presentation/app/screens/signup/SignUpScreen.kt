package com.bakery.bakeryapp.ui.presentation.app.screens.signup

import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.data.viewmodel.login.LoginViewModel
import com.bakery.bakeryapp.data.viewmodel.login.event.UIEvent
import com.bakery.bakeryapp.ui.presentation.app.components.ButtonComponent
import com.bakery.bakeryapp.ui.presentation.app.components.CheckBoxComponent
import com.bakery.bakeryapp.ui.presentation.app.components.ClickableLoginTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.DatePickerComponent
import com.bakery.bakeryapp.ui.presentation.app.components.DividerTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.EmailTextFieldComponent
import com.bakery.bakeryapp.ui.presentation.app.components.HeadingTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.NormalTextComponent
import com.bakery.bakeryapp.ui.presentation.app.components.OutlinedTextFieldComponent
import com.bakery.bakeryapp.ui.presentation.app.components.PasswordTextFieldComponent
import com.bakery.bakeryapp.ui.presentation.app.navigation.AppRouter
import com.bakery.bakeryapp.ui.presentation.app.navigation.Screen

@Composable
fun SingUpScreen(loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(value = stringResource(id = R.string.hello))

            HeadingTextComponent(value = stringResource(id = R.string.create_account))

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.ic_profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.FirstNameChanged(it))
                },
                errorStatus = loginViewModel.state.value.firstNameError
            )

            OutlinedTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource(id = R.drawable.ic_profile),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.LastNameChanged(it))
                },
                errorStatus = loginViewModel.state.value.lastNameError
            )

            DatePickerComponent(
                value = stringResource(id = R.string.date_pick),
                painterResource(id = R.drawable.ic_calendar),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.BirthDayChanged(it))
                },
                errorStatus = loginViewModel.state.value.birthDayError
            )

            EmailTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.ic_email),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.state.value.emailError
            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))
                },
                errorStatus = loginViewModel.state.value.passwordError
            )

            CheckBoxComponent(
                value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                },
                onCheckedChanged = {
                    loginViewModel.onEvent(UIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
                },
                isEnabled = loginViewModel.allValidationsPassed.value
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(
                tryingToLogin = true,
                onTextSelected = {
                    AppRouter.navigateTo(Screen.LoginScreen)
                }
            )
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_UNDEFINED, showBackground = true)
// @Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun DefaultPreviewOfSingUpScreen() {
    SingUpScreen()
}
