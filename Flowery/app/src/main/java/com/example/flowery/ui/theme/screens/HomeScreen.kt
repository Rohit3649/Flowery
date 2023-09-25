package com.example.flowery.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.example.flowery.model.listOfFlowers
import com.example.flowery.ui.theme.LightGreen
import com.example.flowery.ui.theme.Pink
import com.example.flowery.ui.theme.PinkLight
import com.example.flowery.ui.theme.backGround
import com.example.flowery.ui.theme.component.ImageComponent
import com.example.flowery.ui.theme.component.TextComponentString
import com.example.flowery.ui.theme.navigation.ProductDetail


@Composable
fun HomeScreen(navHostController: NavHostController) {
    val searchText = rememberSaveable {
        mutableStateOf("")
    }

    var flowerNameList by remember {
        mutableStateOf(listOfFlowers)
    }
    var flowerList by remember {
        mutableStateOf(listOfFlowers)
    }

    Box(
        modifier = Modifier
            .background(backGround)
            .fillMaxSize()
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Header()
            SpacerHeight(20.dp)
            Box {
                SearchField(text = searchText.value) {
                    searchText.value = it
                }
            }

            Column(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                LazyRow() {
                    items(flowerNameList.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .background(
                                    color = if (flowerNameList[index].isSelected) Pink else PinkLight,
                                    shape = RoundedCornerShape(size = 10.dp),
                                )
                                .clickable {
                                    flowerNameList =
                                        flowerNameList.mapIndexed { selectedIndex, item ->
                                            if (index == selectedIndex) {
                                                item.copy(isSelected = !item.isSelected)
                                            } else {
                                                item
                                            }
                                        }
                                }
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            TextComponentString(
                                text = flowerNameList[index].name,
                                fontSize = 15.sp,
                                color = if (flowerNameList[index].isSelected) Color.White else Color.Black,
                                fontWeight = if (flowerNameList[index].isSelected) FontWeight.W600 else FontWeight.W300
                            )
                        }
                    }
                }
            }
            PopularAndSeeAll()
            SpacerHeight(20.dp)

            LazyRow() {
                items(flowerList, key = {
                    it.id
                }) {
                    FlowerCardItemLayout(it) {
                        navHostController.currentBackStackEntry?.savedStateHandle?.set("data", it)
                        navHostController.navigate(ProductDetail)
                    }
                }
            }

        }
    }
}

@Composable
fun FlowerCardItemLayout(
    flower: Flower,
    onFlowerCardClick: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(width = 1.dp, color = PinkLight),
        modifier = Modifier
            .width(200.dp)
            .padding(end = 10.dp)
            .clickable { onFlowerCardClick() },
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        RoundedCornerShape(bottomStart = 14.dp, bottomEnd = 14.dp)
                    )
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                ImageComponent(
                    image = flower.imageLink,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            TextComponentString(
                text = flower.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.W500
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextComponentString(
                    text = (flower.price * 100).toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W400
                )
                IconButton(onClick = {
                    selected = !selected
                }, modifier = Modifier.size(24.dp)) {
                    IconComponentDrawable(
                        icon = R.drawable.love,
                        size = 20.dp,
                        tint = if (selected) Color.Red else Color.Unspecified
                    )
                }
            }
        }
    }
}

@Composable
fun PopularAndSeeAll() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponentString(
            text = "Popular", fontSize = 20.sp,
            fontWeight = FontWeight.W400
        )
        TextComponentString(
            text = "See All",
            fontSize = 20.sp,
            color = Pink,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChanged,
        shape = RoundedCornerShape(26.dp),
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = PinkLight,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ),
        placeholder = {
            TextComponentString(
                text = "Search",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.W400
            )
        },
        leadingIcon = {
            IconComponentImageVector(
                icon = Icons.Default.Search,
                size = 25.dp
            )
        },
        trailingIcon = {
            IconComponentDrawable(
                icon = R.drawable.filter,
                size = 25.dp,
                tint = Color.Black
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            autoCorrect = true
        )
    )
}


@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(PinkLight, CircleShape)
                .size(46.dp),
            contentAlignment = Alignment.Center
        ) {
            IconComponentImageVector(icon = Icons.Default.Menu, size = 30.dp)
        }
        IconComponentDrawable(icon = R.drawable.flower_logo, size = 30.dp)
        Box(
            modifier = Modifier
                .background(PinkLight, CircleShape)
                .size(46.dp),
            contentAlignment = Alignment.Center
        ) {
            IconComponentImageVector(icon = Icons.Default.ShoppingCart, size = 30.dp)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        TextComponentString(text = "Our way for loving you back", fontSize = 20.sp)
    }
}

@Composable
private fun IconComponentDrawableTemp() {
    Box {
        IconComponentDrawable(
            icon = R.drawable.filter,
            size = 25.dp,
            modifier = Modifier
                .align(Alignment.CenterEnd) //This doesn't take align End //important //works with Box
                .size(25.dp)
                .padding(end = 10.dp),
            tint = Color.Black
        )
    }
}

@Composable
private fun LoadDataFromFile() {
    // If want to read data from Json file
    /*  val mContext = LocalContext.current
        LaunchedEffect(key1 = Unit) {
            val jsonString = mContext
                .assets
                .open("flowers.json")
                .bufferedReader()
                .use {
                    it.readText()
                }

            flowerState.value = Gson().fromJson(jsonString, Array<Flower>::class.java).asList()
        }*/
}


@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewHomeScreen() {
    HomeScreen(rememberNavController())
}