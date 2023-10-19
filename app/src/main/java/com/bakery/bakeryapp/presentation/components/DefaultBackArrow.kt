package com.bakery.bakeryapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.bakery.bakeryapp.R

@Composable
fun DefaultBackArrow(
    onClick: () -> Unit
) {
    /*Box(
        modifier = Modifier
            .size(30.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back_icon),
            contentDescription = "Arrow Back",
            modifier = Modifier.size(20.dp)
        )
    }*/

    IconButton(
        onClick = { onClick() },
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            )
            .clip(CircleShape)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_back_icon), contentDescription = null)
    }
}
