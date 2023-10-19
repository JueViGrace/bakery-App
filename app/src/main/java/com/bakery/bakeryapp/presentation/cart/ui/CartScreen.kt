package com.bakery.bakeryapp.presentation.cart.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bakery.bakeryapp.R
import com.bakery.bakeryapp.presentation.components.CustomDefaultBtn
import com.bakery.bakeryapp.presentation.components.DefaultBackArrow

// TODO: LOGIG
@Composable
fun CartScreen(
    onBackPressed: () -> Unit,
    checkOutPressed: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize(1f)) {
        val (topBar, product, checkout) = createRefs()

        TopBarComponent(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            arrowBackPressed = {
                onBackPressed()
            }
        )

        ProductsComponent(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(product) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        CheckOutComponent(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(checkout) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                )
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .padding(20.dp),
            checkOutBtnPressed = {
                checkOutPressed()
            }
        )
    }
}

@Composable
fun CheckOutComponent(modifier: Modifier, checkOutBtnPressed: () -> Unit) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_receipt),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(45.dp)
                    .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(15.dp))
                    .padding(10.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text("Add vouture Code")
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "$12312311",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier
                    .width(150.dp)
            ) {
                CustomDefaultBtn(shapeSize = 15f, btnText = "Check Out") {
                    checkOutBtnPressed()
                }
            }
        }
    }
}

@Composable
fun ProductsComponent(modifier: Modifier) {
    // var itemDrag by remember { mutableFloatStateOf(0f) }
    Box(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(count = 199) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        /*.pointerInput(Unit) {
                            detectVerticalDragGestures { change, dragAmount ->
                                itemDrag = dragAmount
                            }
                        }*/
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_bag),
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Column {
                        Text(
                            text = "Wireless Controller for PS4™",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text(
                                text = "$79.99",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "  x1", color = MaterialTheme.colorScheme.onBackground)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBarComponent(modifier: Modifier, arrowBackPressed: () -> Unit) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(0.5f)) {
            DefaultBackArrow {
                arrowBackPressed()
            }
        }
        Box(modifier = Modifier.weight(0.7f)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.your_cart),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                // AGREGAR CUENTA DE ITEMS
                Text(
                    text = "4 items",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}
