package com.example.flowery.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.flowery.R
import com.example.flowery.model.Flower
import com.example.flowery.ui.theme.Pink
import com.example.flowery.ui.theme.PinkLight
import com.example.flowery.ui.theme.component.ImageComponent
import com.example.flowery.ui.theme.component.TextComponentString

@Composable
fun ProductDetailScreen(
    navHostController: NavHostController
) {
    val flower =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Flower>("data") ?: Flower()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            DetailScreenHeader {
                navHostController.navigateUp()
            }
            SpacerHeight(30.dp)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    ShowProduct(flower)
                    ProductDescription(flower)
                }
            }
        }
        ButtonComponent(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        ) {

        }
    }
}

@Composable
fun ProductDescription(
    flower: Flower,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp, horizontal = 30.dp)
    ) {

        TextComponentString(text = "Flower", fontSize = 18.sp, fontWeight = FontWeight.W400)

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextComponentString(
                text = flower.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier.weight(0.8f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
            ) {
                IconComponentDrawable(
                    icon = R.drawable.star,
                    size = 20.dp,
                    modifier = Modifier
                        .align(CenterVertically)
                )
                Spacer(modifier = Modifier.width(5.dp))
                TextComponentString(
                    text = "4.5",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        TextComponentString(
            text = flower.detailInformation,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
        )
    }
}

@Composable
private fun ButtonComponent(
    modifier: Modifier = Modifier,
    onAddBagClicked:() -> Unit
) {
    Button(
        onClick = onAddBagClicked,
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Pink
        ),
        shape = RoundedCornerShape(37.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {

        TextComponentString(text = "Add to Bag", fontSize = 20.sp, fontWeight = FontWeight.W500,)
    }

}

@Composable
fun ShowProduct(
    flower: Flower
) {

    var counter by remember { mutableIntStateOf(0) }
    Box(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .border(border = BorderStroke(width = 1.dp, color = PinkLight))
            .background(
                PinkLight, RoundedCornerShape(14.dp)
            ),
        contentAlignment = Center
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageComponent(
                image = flower.imageLink,
                modifier = Modifier
                    .width(200.dp)
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .background(
                        Color.White,
                        RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Center
            ) {
                Row(
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.padding(horizontal = 3.dp)
                ) {
                    IconComponentDrawable(icon = R.drawable.minus, size = 30.dp) {
                        if (counter > 0)
                            counter--
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextComponentString(
                        text = "$counter",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W500,
                        color = Pink,
                        modifier = Modifier.align(CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    IconComponentDrawable(icon = R.drawable.add, size = 30.dp) {
                        counter++
                    }
                }
            }
        }
    }

}

@Composable
fun DetailScreenHeader(
    onBackClick: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(PinkLight, CircleShape)
                .size(46.dp),
            contentAlignment = Center
        ) {
            IconComponentImageVector(icon = Icons.Default.ArrowBack, size = 30.dp) {
                onBackClick()
            }
        }
        IconComponentDrawable(icon = R.drawable.flower_logo, size = 40.dp)
        Box(
            modifier = Modifier
                .background(PinkLight, CircleShape)
                .size(46.dp),
            contentAlignment = Center
        ) {
            IconButton(onClick = {
                selected = !selected
            }, modifier = Modifier.size(24.dp)) {
                IconComponentDrawable(
                    icon = R.drawable.love,
                    size = 30.dp,
                    tint = if (selected) Color.Red else Color.Unspecified
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewProductDetailScreen() {
    ProductDetailScreen(rememberNavController())
}