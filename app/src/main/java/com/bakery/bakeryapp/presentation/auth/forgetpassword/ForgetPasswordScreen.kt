package com.bakery.bakeryapp.presentation.auth.forgetpassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.domain.rules.Validator
import com.bakery.bakeryapp.navigation.authgraph.AuthScreen
import com.bakery.bakeryapp.presentation.components.CustomDefaultBtn
import com.bakery.bakeryapp.presentation.components.CustomTextField
import com.bakery.bakeryapp.presentation.components.DefaultBackArrow

@Composable
fun ForgetPasswordScreen(
    navController: NavController
) {
    var email by remember {
        mutableStateOf("")
    }
    val emailErrorState = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TODO: MAKE TOOLBAR CUSTOM
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(0.3f)) {
                DefaultBackArrow {
                    navController.navigate(AuthScreen.LoginScreen.route) {
                        popUpTo(AuthScreen.ForgetPasswordScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = "Forget Password",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Forget Password", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Please enter your email and we will send\nyou a link to return your account",
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(150.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                placeholder = "example@email.com",
                trailingIcon = R.drawable.ic_email,
                label = "Email",
                errorState = emailErrorState,
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                onChanged = { newEmail ->
                    email = newEmail.text
                }
            )

            CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
                // email pattern

                val isEmailValid = Validator.validateEmail(email)
                emailErrorState.value = !isEmailValid.status
                if (isEmailValid.status) {
                    navController.navigate(AuthScreen.LoginScreen.route) {
                        popUpTo(AuthScreen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(AuthScreen.SignUpScreen.route)
                    }
                )
            }
        }
    }
}
