package com.bakery.bakeryapp.presentation.product.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories
import com.bakery.bakeryapp.presentation.components.DefaultBackArrow
import com.bakery.bakeryapp.presentation.product.ProductDetailState
import com.bakery.bakeryapp.presentation.product.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
    popBack: () -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<ProductDetailState>(
        initialValue = ProductDetailState.Loading,
        key1 = lifecycle,
        key2 = productDetailViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            productDetailViewModel.state.collect { value = it }
        }
    }
    // TODO: CUSTOM COMPONENT FOR ERROR && LOADING
    when (uiState) {
        is ProductDetailState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (uiState as ProductDetailState.Error).error, fontSize = 24.sp)
            }
        }
        ProductDetailState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is ProductDetailState.Success -> {
            ProductComponent(success = (uiState as ProductDetailState.Success).success) {
                popBack()
            }
        }
    }
}

@Composable
fun ProductComponent(success: ProductsWithCategories, backPressed: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductDetailTop(product = success) {
            backPressed()
        }
    }
}

@Composable
fun ProductDetailTop(product: ProductsWithCategories, iconPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultBackArrow {
            iconPressed()
        }
        RatingComponent(product = product)
    }
}
