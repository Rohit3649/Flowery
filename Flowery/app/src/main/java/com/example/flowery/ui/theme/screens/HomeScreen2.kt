package com.example.flowery.ui.theme.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentDrawableNoRippleEffect
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth
import com.example.flowery.R
import com.example.flowery.model.Bouquets
import com.example.flowery.model.Flower
import com.example.flowery.model.listOfBouquet
import com.example.flowery.model.listOfFlowers
import com.example.flowery.ui.theme.Pink
import com.example.flowery.ui.theme.PurpleGrey40
import com.example.flowery.ui.theme.backGround
import com.example.flowery.ui.theme.component.ImageComponent
import com.example.flowery.ui.theme.component.TextComponentString
import com.example.flowery.ui.theme.navigation.ProductDetail2


@Composable
fun HomeScreen2(navHostController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(backGround)
    ) {
        item {
            CreateHeaderComponent()
            SpacerHeight(30.dp)
            CreateSearchComponent(searchText) {
                searchText = it
            }
            SpacerHeight(30.dp)
            CommonRowHeader("Categories", "See All")
            CategoryImageComponent() {
                navHostController.navigate(ProductDetail2)
            }
            SpacerHeight()
            CommonRowHeader("Popular", "See All")
            PopularImageComponent()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularImageComponent() {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        listOfBouquet.forEach {
            PopularImageComponentItem(it, modifier = Modifier) {

            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularImageComponentItem(
    bouquet: Bouquets,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clickable { onClick() }
            .width(141.dp)
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .width(141.dp)
                .height(149.dp)
        ) {
            ImageComponent(
                image = bouquet.imageLink,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            IconComponentDrawableNoRippleEffect(
                icon = R.drawable.love, size = 32.dp,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopEnd),
                tint = if (selected) Color.Red else Color.Unspecified
            ) {
                selected = !selected
            }
        }
        SpacerHeight(10.dp)
        FilterChipLayout(bouquet.tags)
    }
}

@Composable
fun FilterChipLayout(
    tags: List<String>
) {
    val originalChips by remember { mutableStateOf(tags) }
    val temp: Set<Int> = emptySet()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FilterChipEachRow(chipList = originalChips, tempList = temp)
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterChipEachRow(
    chipList: List<String>,
    tempList: Set<Int>
) {
    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
        //.padding(end = 5.dp),
    ) {
        chipList.forEachIndexed { index, s ->

            FilterChip(
                selected = multipleChecked.contains(index),
                onClick = {
                    multipleChecked = if (multipleChecked.contains(index))
                        multipleChecked.minus(index)
                    else
                        multipleChecked.plus(index)

                },
                label = {
                    TextComponentString(text = s, fontSize = 10.sp)
                },
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (!multipleChecked.contains(index)) PurpleGrey40 else Color.Transparent,
                    borderWidth = if (multipleChecked.contains(index)) 0.dp else 1.dp
                ),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    (if (multipleChecked.contains(index)) Icons.Default.Check else null)?.let {
                        Icon(it, contentDescription = "")
                    }
                },
                modifier = Modifier.padding(end = 3.dp)
            )

        }
    }

}

@Composable
fun CategoryImageComponent(
    onClick: (Flower) -> Unit
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(items = listOfFlowers, key = {
            it.id
        }) { it ->
            CategoryImageComponentItem(it) { item ->
                onClick(item)
            }
        }
    }
}

@Composable
fun CategoryImageComponentItem(
    flower: Flower,
    onClicked: (Flower) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClicked(flower) }
            .width(150.dp)
            .height(200.dp)
            .padding(end = 20.dp)
            .border(border = BorderStroke(1.dp, Color.Black)),
    ) {
        Text(
            text = flower.name,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp)
        )

        Image(
            painter = painterResource(id = flower.imageLink),
            contentDescription = "Image",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(end = 8.dp)
                .height(150.dp)
        )
    }
}

@Composable
fun CommonRowHeader(
    first: String,
    second: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextComponentString(text = first, fontSize = 20.sp, fontWeight = FontWeight.W500)
        // instead of Row we can create TextButton (inside call Text and Icon) as well. It has RowScope - so by default
        // it will be placed one after another
        TextButton(onClick = { /*TODO*/ }) {

        }
        Row {
            TextComponentString(
                text = second,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
                color = Pink
            )
            SpacerWidth(3.dp)
            IconComponentImageVector(
                icon = Icons.Default.ArrowForward, size = 25.dp,
                tint = Pink
            )
        }
    }
}

@Composable
fun CategoryHeaderComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextComponentString(text = "Categories", fontSize = 20.sp, fontWeight = FontWeight.W500)
        // instead of Row we can create TextButton (inside call Text and Icon) as well. It has RowScope - so by default
        // it will be placed one after another
        TextButton(onClick = { /*TODO*/ }) {

        }
        Row {
            TextComponentString(
                text = "See All",
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
                color = Pink
            )
            SpacerWidth(3.dp)
            IconComponentImageVector(
                icon = Icons.Default.ArrowForward, size = 25.dp,
                tint = Pink
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSearchComponent(
    searchText: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(1.dp, Color.Black), RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(10.dp)),
        singleLine = true,
        shape = RoundedCornerShape(5.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            containerColor = Color.White,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified
        ),
        placeholder = {
            TextComponentString(
                text = "search Flowers, bouquets etc.",
                fontSize = 15.sp, fontWeight = FontWeight.W400,
                color = Color.Black
            )
        },
        leadingIcon = {
            IconComponentImageVector(
                icon = Icons.Default.Search,
                size = 30.dp
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Composable
fun CreateHeaderComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextComponentString(
            text = "Choose a perfect \n gift for loved one",
            fontSize = 24.sp,
            fontWeight = FontWeight.W600
        )
        IconComponentImageVector(icon = Icons.Default.Notifications, size = 40.dp, tint = Pink)
    }
}


@Preview(
    showBackground = true,
    name = "preview"/*,
    widthDp = 200,
    heightDp = 200*/
)
@Composable
fun ShowPreviewHomeScreen2() {
    HomeScreen2(rememberNavController())
}