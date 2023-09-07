package com.bakery.bakeryapp.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bakery.bakeryapp.ui.presentation.app.BakeryAppComposable
import com.bakery.bakeryapp.ui.presentation.theme.BakeryAppTheme
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
