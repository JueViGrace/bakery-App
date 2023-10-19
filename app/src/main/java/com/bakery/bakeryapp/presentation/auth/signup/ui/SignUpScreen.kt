package com.bakery.bakeryapp.presentation.auth.signup.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bakery.bakeryapp.R
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
import com.bakery.bakeryapp.presentation.auth.signup.events.SignUpUIEvent
import com.bakery.bakeryapp.presentation.auth.signup.viewmodel.SignUpViewModel

@Composable
fun SingUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToTerms: () -> Unit
) {
    val events = signUpViewModel::onEvent
    val state = signUpViewModel.state.collectAsStateWithLifecycle()

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
                        events(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = state.value.firstNameError
                )

                OutlinedTextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource(id = R.drawable.ic_profile),
                    onTextSelected = {
                        events(SignUpUIEvent.LastNameChanged(it))
                    },
                    errorStatus = state.value.lastNameError
                )

                DatePickerComponent(
                    value = stringResource(id = R.string.date_pick),
                    painterResource(id = R.drawable.ic_calendar),
                    onTextSelected = {
                        events(SignUpUIEvent.BirthDayChanged(it))
                    },
                    errorStatus = state.value.birthDayError
                )

                PhoneTextFieldComponent(
                    labelValue = stringResource(id = R.string.phone_number),
                    painterResource(id = R.drawable.ic_call),
                    onTextSelected = {
                        events(SignUpUIEvent.PhoneChanged(it))
                    },
                    errorStatus = state.value.phoneError
                )

                EmailTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.ic_email),
                    onTextSelected = {
                        events(SignUpUIEvent.EmailChanged(it))
                    },
                    errorStatus = state.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        events(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = state.value.passwordError
                )

                CheckBoxComponent(
                    onTextSelected = {
                        navigateToTerms()
                    },
                    onCheckedChanged = {
                        events(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        events(SignUpUIEvent.RegisterButtonClicked)
                    },
                    isEnabled = state.value.allValidationsPassed
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        navigateToLogin()
                    }
                )

                if (state.value.signUpError) {
                    Toast.makeText(
                        LocalContext.current,
                        state.value.signUpMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (state.value.signUpInProgress) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        if (state.value.signedUp) {
            navigateToHome()
        }
    }
}
