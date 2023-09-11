package com.bakery.bakeryapp.ui.screens.login

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.constantes.Constantes
import com.bakery.bakeryapp.data.repository.datastore.DataStoreViewModel
import com.bakery.bakeryapp.navigation.AppRouter
import com.bakery.bakeryapp.navigation.Screen
import com.bakery.bakeryapp.ui.components.ButtonComponent
import com.bakery.bakeryapp.ui.components.ClickableLoginTextComponent
import com.bakery.bakeryapp.ui.components.DividerTextComponent
import com.bakery.bakeryapp.ui.components.EmailTextFieldComponent
import com.bakery.bakeryapp.ui.components.HeadingTextComponent
import com.bakery.bakeryapp.ui.components.NormalTextComponent
import com.bakery.bakeryapp.ui.components.PasswordTextFieldComponent
import com.bakery.bakeryapp.ui.components.UnderlinedTextComponent
import com.bakery.bakeryapp.ui.uievents.login.LoginUIEvent
import com.bakery.bakeryapp.ui.viewmodel.login.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel(), dataStoreViewModel: DataStoreViewModel = viewModel()) {
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
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                NormalTextComponent(value = stringResource(id = R.string.welcome))

                HeadingTextComponent(value = stringResource(id = R.string.login))

                Spacer(modifier = Modifier.height(80.dp))

                EmailTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.ic_email),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.state.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.state.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                UnderlinedTextComponent(value = stringResource(R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LogingButtonClicked)
                    },
                    loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    AppRouter.navigateTo(Screen.SingUpScreen)
                })

                if (loginViewModel.state.value.loginError) {
                    Toast.makeText(
                        LocalContext.current,
                        loginViewModel.state.value.loginMessage,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        if (loginViewModel.loginInProgress.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                dataStoreViewModel.storePreference(Constantes.ACCESS_TOKEN, loginViewModel.state.value.accessToken)
                dataStoreViewModel.storePreference(Constantes.COD_USUARIO, loginViewModel.state.value.userId)
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
