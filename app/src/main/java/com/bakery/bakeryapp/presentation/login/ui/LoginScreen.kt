package com.bakery.bakeryapp.presentation.login.ui

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
import com.bakery.bakeryapp.presentation.components.ClickableLoginTextComponent
import com.bakery.bakeryapp.presentation.components.DividerTextComponent
import com.bakery.bakeryapp.presentation.components.EmailTextFieldComponent
import com.bakery.bakeryapp.presentation.components.HeadingTextComponent
import com.bakery.bakeryapp.presentation.components.NormalTextComponent
import com.bakery.bakeryapp.presentation.components.PasswordTextFieldComponent
import com.bakery.bakeryapp.presentation.components.UnderlinedTextComponent
import com.bakery.bakeryapp.presentation.login.events.LoginUIEvent
import com.bakery.bakeryapp.presentation.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit
) {
    val events = loginViewModel::onEvent
    val state = loginViewModel.state.collectAsStateWithLifecycle()

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
                NormalTextComponent(value = stringResource(id = R.string.welcome))

                HeadingTextComponent(value = stringResource(id = R.string.login))

                Spacer(modifier = Modifier.height(80.dp))

                EmailTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.ic_email),
                    onTextSelected = {
                        events(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = state.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        events(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = state.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                UnderlinedTextComponent(value = stringResource(R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        events(LoginUIEvent.LogingButtonClicked)
                    },
                    isEnabled = state.value.allValidationsPassed
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navigateToRegister()
                })

                if (state.value.loginError) {
                    Toast.makeText(
                        LocalContext.current,
                        state.value.loginMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (state.value.loginInProgress) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                /*dataStoreViewModel.storePreference(
                    Constantes.ACCESS_TOKEN,
                    loginViewModel.state.value.accessToken
                )
                dataStoreViewModel.storePreference(
                    Constantes.COD_USUARIO,
                    loginViewModel.state.value.userId
                )*/
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        if (state.value.loggedIn) {
            navigateToHome()
        }
    }
}
