package com.example.flowery.ui.theme.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerScope
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chattingappui.ui.theme.component.IconComponentDrawable
import com.example.chattingappui.ui.theme.component.IconComponentImageVector
import com.example.chattingappui.ui.theme.component.SpacerHeight
import com.example.chattingappui.ui.theme.component.SpacerWidth
import com.example.flowery.R
import com.example.flowery.model.productDetailChips
import com.example.flowery.model.ratingPersonList
import com.example.flowery.ui.theme.Pink
import com.example.flowery.ui.theme.PinkLight
import com.example.flowery.ui.theme.component.ImageComponent
import com.example.flowery.ui.theme.component.TextComponentString
import com.example.flowery.ui.theme.navigation.Cart
import kotlin.math.absoluteValue

@Composable
fun ProductDetailScreen2(navHostController: NavHostController) {
    var selectedChip by remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageComponent(
            image = R.drawable.room_deco,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth
        )
        HeaderComponent(
            onBackClicked = {
                navHostController.navigateUp()
            },
            onShareClicked = {

            }
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 230.dp)
                .background(Color.White, RoundedCornerShape(15.dp))
        ) {
            LazyColumn(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 80.dp)
            ) {
                item {
                    ProductInfoHeader()
                    RatingRow()
                    SpacerHeight(20.dp)
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        productDetailChips.forEachIndexed { index, chip ->
                            ProductDetailChipItem(
                                title = chip,
                                selected = index == selectedChip,
                                index = index,
                                onClicked = {
                                    selectedChip = it
                                }
                            )
                        }
                    }
                    SpacerHeight(5.dp)
                    ChipDetail(selectedChip)
                    SpacerHeight(15.dp)
                    Divider(modifier = Modifier.fillMaxWidth(), color = Pink, thickness = 2.dp)
                    SpacerHeight(20.dp)
                    RecommendedProducts()
                }
            }
            AddToBagComponent(modifier = Modifier.align(Alignment.BottomCenter)) {
                navHostController.navigate(Cart)
            }
        }
    }
}

@Composable
fun AddToBagComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp).fillMaxWidth()
    ) {
        Divider(modifier = Modifier.fillMaxWidth(), color = Pink, thickness = 2.dp)
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = {},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .size(40.dp),
                border = BorderStroke(1.dp, Pink),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                )
            ) {
                IconComponentImageVector(
                    icon = Icons.Default.FavoriteBorder,
                    size = 20.dp,
                    tint = Pink
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
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                TextComponentString(
                    text = "Add To Bag",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            }
        }
    }
}

@Composable
fun RecommendedProducts() {
    Column {
        TextComponentString(
            text = "Check Out other images",
            fontSize = 23.sp,
            fontWeight = FontWeight.W600
        )
        Box(
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            ViewPagerComponent()
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerComponent() {
    val images = remember {
        mutableStateListOf(
            R.drawable.decoration2,
            R.drawable.decoration3,
            R.drawable.decoration4,
            R.drawable.decoration5,
            R.drawable.bouquet5,
            R.drawable.bouquet6
        )
    }

    val pagerState = rememberPagerState(
        pageCount = {
            images.size
        })

    var activePhotoId by rememberSaveable { mutableStateOf<Int?>(null) }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 35.dp),
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {

                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Box(
                modifier = Modifier.clickable {
                    activePhotoId = images[page]
                }
            ) {
/*                ImageComponent(image = R.drawable.bouquet3,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop)*/
                ImageComponent(
                    image = images[page],
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

//https://github.com/android/snippets/blob/3f009192432af7d8d045aa1e2c79ff5dc14f47b5/compose/snippets/src/main/java/com/example/compose/snippets/layouts/PagerSnippets.kt#L252-L278
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ViewPagerComponent1() {
    val images = remember {
        mutableStateListOf(
            R.drawable.bouquet1,
            R.drawable.bouquet2,
            R.drawable.bouquet3,
            R.drawable.bouquet4,
            R.drawable.bouquet5,
            R.drawable.bouquet6
        )
    }

    val pagerState = rememberPagerState(
        pageCount = {
            images.size
        })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 80.dp),
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fixed(250.dp),
    ) { pageIndex ->
        Card(
            modifier = Modifier
                .size(400.dp)
                .graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue

                    //Log.w("FLOWERY", "pageIndex $pageIndex AND pageOffset $pageOffset")
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleX = scale
                    }

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxSize()
                .aspectRatio(1f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                ImageComponent(
                    image = images[pageIndex],
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                ProfilePictureCard(
                    images[pageIndex],
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .offset {
                            // calculate offset for the current page from the scroll position
                            val offSet =
                                (pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction

                            IntOffset(
                                x = (36.dp * offSet).roundToPx(),
                                y = 0
                            )
                        }
                )
            }
        }
    }

    /*    HorizontalPager(state = pagerState) { page ->
            Card(
                Modifier
                    .size(250.dp)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                ImageComponent(image = images[page])
            }
        }*/


    /*    val pagerState = rememberPagerState(pageCount = {
            4
        })
        HorizontalPager(state = pagerState) { page ->
            Card(
                Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = (
                                (pagerState.currentPage - page) + pagerState
                                    .currentPageOffsetFraction
                                ).absoluteValue

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                ImageComponent(image = R.drawable.bouquet2)
            }
        }*/
}

@Composable
fun ProfilePictureCard(
    @DrawableRes image: Int,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(4.dp, MaterialTheme.colorScheme.surface)
    ) {
        var offSetX by remember { mutableStateOf(0f) }
        var offSetY by remember { mutableStateOf(0f) }
        ImageComponent(
            image = image,
            modifier = Modifier
                .size(72.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        offSetX += dragAmount.x
                        offSetY += dragAmount.y
                    }
                },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ChipDetail(
    selectedChip: Int
) {
    TextComponentString(
        text = when (selectedChip) {
            0 -> stringResource(id = R.string.detail_screen_chip_description)
            1 -> stringResource(id = R.string.detail_screen_chip_material)
            2 -> stringResource(id = R.string.detail_screen_chip_review)
            else -> "Random Text"
        },
        fontSize = 14.sp,
        fontWeight = FontWeight.W300
    )
}

@Composable
fun ProductDetailChipItem(
    title: String,
    selected: Boolean,
    index: Int,
    modifier: Modifier = Modifier,
    onClicked: (Int) -> Unit
) {
    TextButton(
        onClick = { onClicked(index) },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Pink else Color.White,
            contentColor = if (selected) Color.White else Color.Black
        ),
        border = BorderStroke(1.dp, if (selected) Color.Black else Pink),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 4.dp
        )
    ) {
        TextComponentString(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400
        )
    }
}

@Composable
fun RatingRow() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = PinkLight,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(4) {
                        ImageComponent(image = R.drawable.star)
                        SpacerWidth(4.dp)
                    }
                    ImageComponent(
                        image = R.drawable.star,
                        modifier = Modifier.drawWithContent {
                            if (layoutDirection == LayoutDirection.Rtl) {
                                clipRect(left = size.width / 2f) {
                                    this@drawWithContent.drawContent()
                                }
                            } else {
                                clipRect(right = size.width / 2f) {
                                    this@drawWithContent.drawContent()
                                }
                            }
                        }
                    )
                    TextComponentString(
                        text = "4.5", fontSize = 12.sp,
                        fontWeight = FontWeight.W400
                    )
                }
                SpacerHeight(10.dp)
                Row {
                    TextComponentString(
                        text = "98 Reviews", fontSize = 12.sp,
                        fontWeight = FontWeight.W400
                    )
                    SpacerWidth(3.dp)
                    IconComponentImageVector(icon = Icons.Default.KeyboardArrowRight, size = 16.dp)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                ratingPersonList.forEachIndexed { index, i ->
                    IconComponentDrawable(
                        icon = i, size = 35.dp,
                        modifier = Modifier.offset(
                            x = if (index == ratingPersonList.size) 0.dp else -(index * 10).dp,
                            y = 0.dp
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun ProductInfoHeader() {
    val count = remember() { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        TextComponentString(
            text = "White Rose Night",
            fontSize = 25.sp,
            fontWeight = FontWeight.W500
        )
        SpacerHeight(10.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextComponentString(
                text = "$599", fontSize = 25.sp, fontWeight = FontWeight.W500,
                color = Pink,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            ProductCountButton(R.drawable.substract_new) {
                if (count.value > 0)
                    count.value--
            }
            TextComponentString(text = count.value.toString(), fontSize = 25.sp)
            ProductCountButton(R.drawable.add_new) {
                count.value++
            }
        }
    }
}

@Composable
fun ProductCountButton(
    @DrawableRes image: Int,
    onClicked: () -> Unit
) {
    TextButton(onClick = onClicked) {
        ImageComponent(image = image)
    }
}

@Composable
fun HeaderComponent(
    onBackClicked: () -> Unit,
    onShareClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconComponentImageVector(icon = Icons.Default.ArrowBack, size = 30.dp) {
            onBackClicked()
        }
        IconComponentImageVector(icon = Icons.Default.Share, size = 30.dp) {
            onShareClicked()
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
fun ShowPreviewProductDetailScreen2() {
    ProductDetailScreen2(rememberNavController())
}