package com.bakery.bakeryapp.presentation.signup.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.presentation.components.ButtonComponent
import com.bakery.bakeryapp.presentation.components.CheckBoxComponent
import com.bakery.bakeryapp.presentation.components.ClickableLoginTextComponent
import com.bakery.bakeryapp.presentation.components.DatePickerComponent
import com.bakery.bakeryapp.presentation.components.DividerTextComponent
import com.bakery.bakeryapp.presentation.components.EmailTextFieldComponent
import com.bakery.bakeryapp.presentation.components.HeadingTextComponent
import com.bakery.bakeryapp.presentation.components.NormalTextComponent
import com.bakery.bakeryapp.presentation.components.OutlinedTextFieldComponent
import com.bakery.bakeryapp.presentation.components.PasswordTextFieldComponent
import com.bakery.bakeryapp.presentation.components.PhoneTextFieldComponent
import com.bakery.bakeryapp.presentation.signup.events.SingUpUIEvent
import com.bakery.bakeryapp.presentation.signup.state.SignUpUIState

@Composable
fun SingUpScreen(
    state: SignUpUIState,
    events: (SingUpUIEvent) -> Unit,
    navigateToHome: (Boolean) -> Unit,
    navigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                NormalTextComponent(value = stringResource(id = R.string.hello))

                HeadingTextComponent(value = stringResource(id = R.string.create_account))

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        events(SingUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = state.firstNameError
                )

                OutlinedTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        events(SingUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = state.lastNameError
                )

                DatePickerComponent(
                    value = stringResource(id = R.string.date_pick),
                    painterResource(id = R.drawable.ic_calendar),
                    onTextSelected = {
                        events(SingUpUIEvent.BirthDayChanged(it))
                    },
                    errorStatus = state.birthDayError
                )

                PhoneTextFieldComponent(
                    labelValue = stringResource(id = R.string.phone_number),
                    painterResource(id = R.drawable.ic_call),
                    onTextSelected = {
                        events(SingUpUIEvent.PhoneChanged(it))
                    },
                    errorStatus = state.phoneError
                )

                EmailTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.ic_email),
                    onTextSelected = {
                        events(SingUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = state.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        events(SingUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = state.passwordError
                )

                CheckBoxComponent(
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChanged = {
                        events(SingUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        events(SingUpUIEvent.RegisterButtonClicked)
                        navigateToHome(state.singedUp)
                    },
                    isEnabled = state.allValidationsPassed
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        navigateToLogin()
                    }
                )

                if (state.singUpError) {
                    Toast.makeText(
                        LocalContext.current,
                        state.singUpMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (state.singUpInProgress) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                /*dataStoreViewModel.storePreference(
                    ACCESS_TOKEN,
                    singUpViewModel.state.value.accessToken
                )
                dataStoreViewModel.storePreference(COD_USUARIO, singUpViewModel.state.value.userId)*/
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
//    SystemBackButtonHandler {
//        AppRouter.navigateTo(Screen.LoginScreen)
//    }
}
