package com.example.flowery.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth
import com.example.flowery.R
import com.example.flowery.model.CheckoutItem
import com.example.flowery.model.CheckoutItemList
import com.example.flowery.ui.theme.Pink
import com.example.flowery.ui.theme.PinkLight
import com.example.flowery.ui.theme.component.TextComponentString

@Composable
fun CartScreen(navHostController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            IconButton(
                onClick = {
                    navHostController.navigateUp()
                },
            ) {
                IconComponentImageVector(
                    icon = Icons.Default.ArrowBack, size = 25.dp)
            }

            TextComponentString(
                text = "Shopping Cart",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.padding(vertical = 15.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(top = 10.dp, bottom = 80.dp)
            ) {
                items(CheckoutItemList, key = { it.id }) {
                    ShoppingEachRow(item = it)
                }

                item {
                    Divider(modifier = Modifier.fillMaxWidth(), color = Pink, thickness = 5.dp)
                    SpacerHeight()
                }
            }
        }
        CheckoutRow(modifier = Modifier.align(BottomCenter)) {
        }
    }
}

@Composable
fun CheckoutRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Divider(modifier = Modifier.fillMaxWidth(), color = Pink, thickness = 2.dp)
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column {
                TextComponentString(text = "Total", fontSize = 12.sp, fontWeight = FontWeight.W400)
                TextComponentString(
                    text = "4000 Rs.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600
                )
            }
            SpacerWidth()
            Button(
                onClick = onClick,
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .weight(0.7f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkLight,
                    contentColor = Color.Black
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                TextComponentString(
                    text = "Proceed to checkout",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            }
        }
    }
}

@Composable
fun ShoppingEachRow(
    item: CheckoutItem
) {
    var count by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = item.image), contentDescription = "",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(80.dp)
                    .align(CenterVertically)
            )
            Column(
                modifier = Modifier.padding(start = 1.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.name, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = Color.Black
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .background(Color.White, CircleShape)
                            .border(1.dp, Pink, CircleShape)
                            .size(30.dp),
                        contentAlignment = Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            Modifier.size(10.dp)
                        )
                    }
                }
                Text(
                    text = item.price,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.weight(1f)
                    ) {
                        ProductCountButton(R.drawable.substract_new) {
                            if (count > 0)
                                count--
                        }
                        Text(
                            text = "$count", modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .align(CenterVertically)
                        )
                        ProductCountButton(R.drawable.add_new) {
                            count++
                        }
                    }

                    TextComponentString(
                        text = item.price, fontSize = 18.sp, fontWeight = FontWeight.W600,
                        modifier = Modifier.align(CenterVertically)
                    )
                    /*                    Text(
                                            text = stringResource(id = R.string._599), style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.W600,
                                                color = DarkOrange
                                            ),
                                            modifier = Modifier
                                                .align(CenterVertically)
                                        )*/
                }
            }
        }
        SpacerHeight(20.dp)
        Divider(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(), color = Pink, thickness = 2.dp
        )

    }
}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewProductCartScreen() {
    CartScreen(rememberNavController())
}