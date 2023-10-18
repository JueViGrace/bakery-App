package com.bakery.bakeryapp.presentation.dashboard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.domain.model.product.Product
import com.bakery.bakeryapp.presentation.dashboard.DashboardViewModel
import com.bakery.bakeryapp.presentation.dashboard.ProductState

@Composable
fun DashboardScreen(
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    val uiState by produceState<ProductState>(
        initialValue = ProductState.Loading,
        key1 = lifecycle,
        key2 = productViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            productViewModel.state.collect { value = it }
        }
    }

    when (uiState) {
        is ProductState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (uiState as ProductState.Error).error, fontSize = 24.sp)
            }
        }
        ProductState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){ CircularProgressIndicator() }
        }
        is ProductState.Success -> {
            DashBoardComponent((uiState as ProductState.Success).success) {
                onItemClick(it)
            }
        }
    }
}

@Composable
fun DashBoardComponent(
    state: List<Product>,
    popularProductState: LazyListState = rememberLazyListState(),
    suggestionProductState: LazyListState = rememberLazyListState(),
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = Color(0xFF4a3298), shape = RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("A Spring Surprise", color = Color.White)
            Text(
                "Cashback 25%",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        /*Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_flash_icon),
                    contentDescription = "Flash Deal",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Flash\nDeal", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bill_icon),
                    contentDescription = "Bill",
                    modifier = androidx.compose.ui.Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Bill", fontSize = 14.sp, textAlign = TextAlign.Center)
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.game_icon),
                    contentDescription = "Game",
                    modifier = androidx.compose.ui.Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Game", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gift_icon),
                    contentDescription = "Daily Gift",
                    modifier = androidx.compose.ui.Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Daily\nGift", fontSize = 14.sp, textAlign = TextAlign.Center)
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.discover),
                    contentDescription = "More",
                    modifier = androidx.compose.ui.Modifier
                        .background(
                            MaterialTheme.colors.PrimaryLightColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "More", fontSize = 14.sp, textAlign = TextAlign.Center)
            }
        }*/

        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Special for you", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        Spacer(modifier = Modifier.height(15.dp))
// special offer cart
        LazyRow(
            state = popularProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Popular Product", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // popular product
        LazyRow(
            state = suggestionProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(state.size) {
                // favourite state rememberable
                var favouriteRemember by remember { mutableStateOf(state[it].haveStock) }

                Column {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                onItemClick(state[it]._id)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(state[it].productImage)
                                .crossfade(enable = true)
                                .crossfade(200)
                                .build(),
                            contentDescription = state[it].name,
                            modifier = Modifier.clip(RoundedCornerShape(10))
                        )
                    }
                    Text(
                        text = state[it].name,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(150.dp)
                    )

                    Row(
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$ ${state[it].price}",
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    shape = CircleShape
                                )
                                .clip(CircleShape)
                                .clickable {
                                    favouriteRemember = !favouriteRemember
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(
                                    id = if (favouriteRemember) {
                                        R.drawable.ic_heart_icon_2
                                    } else {
                                        R.drawable.ic_heart_icon
                                    }
                                ),
                                contentDescription = "Favourite Icon",
                                modifier = Modifier.padding(3.dp),
                                colorFilter = if (favouriteRemember) {
                                    ColorFilter.tint(
                                        Color.Red
                                    )
                                } else {
                                    null
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
