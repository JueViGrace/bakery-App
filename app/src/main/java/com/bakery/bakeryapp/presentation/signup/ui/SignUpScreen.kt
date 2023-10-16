package com.bakery.bakeryapp.presentation.signup.ui

import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.constantes.Constantes.ACCESS_TOKEN
import com.bakery.bakeryapp.constantes.Constantes.COD_USUARIO
import com.bakery.bakeryapp.data.repository.datastore.DataStoreViewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.navigation.SystemBackButtonHandler
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
import com.bakery.bakeryapp.presentation.signup.viewmodel.SingUpViewModel

@Composable
fun SingUpScreen(
    singUpViewModel: SingUpViewModel = viewModel(),
    dataStoreViewModel: DataStoreViewModel = viewModel()
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
                        singUpViewModel.onEvent(SingUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.firstNameError
                )

                OutlinedTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        singUpViewModel.onEvent(SingUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.lastNameError
                )

                DatePickerComponent(
                    value = stringResource(id = R.string.date_pick),
                    painterResource(id = R.drawable.ic_calendar),
                    onTextSelected = {
                        singUpViewModel.onEvent(SingUpUIEvent.BirthDayChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.birthDayError
                )

                PhoneTextFieldComponent(
                    labelValue = stringResource(id = R.string.phone_number),
                    painterResource(id = R.drawable.ic_call),
                    onTextSelected = {
                        singUpViewModel.onEvent(SingUpUIEvent.PhoneChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.phoneError
                )

                EmailTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.ic_email),
                    onTextSelected = {
                        singUpViewModel.onEvent(SingUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        singUpViewModel.onEvent(SingUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = singUpViewModel.state.value.passwordError
                )

                CheckBoxComponent(
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    },
                    onCheckedChanged = {
                        singUpViewModel.onEvent(SingUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        singUpViewModel.onEvent(SingUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = singUpViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        AppRouter.navigateTo(Screen.LoginScreen)
                    }
                )

                if (singUpViewModel.state.value.singUpError) {
                    Toast.makeText(
                        LocalContext.current,
                        singUpViewModel.state.value.singUpMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (singUpViewModel.singUpInProgress.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                dataStoreViewModel.storePreference(
                    ACCESS_TOKEN,
                    singUpViewModel.state.value.accessToken
                )
                dataStoreViewModel.storePreference(COD_USUARIO, singUpViewModel.state.value.userId)
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.LoginScreen)
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_UNDEFINED, showBackground = true)
// @Preview(name = "Full Preview", showSystemUi = true)
@Composable
fun DefaultPreviewOfSingUpScreen() {
    SingUpScreen()
}
