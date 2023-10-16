package com.bakery.bakeryapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bakery.bakeryapp.application.BakeryAppComposable
import com.bakery.bakeryapp.presentation.ui.theme.BakeryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BakeryAppTheme {
                BakeryAppComposable()
            }
        }
    }
}
