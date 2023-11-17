package com.chacha.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.chacha.presentation.R
import com.chacha.presentation.extensions.greeting


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
   Scaffold(
       topBar = {
            HomeToolbar()
       }
   ) { paddingValues ->
       Column(
           modifier = Modifier
               .padding(paddingValues)
               .fillMaxSize(),
           verticalArrangement = Arrangement.Top,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           LazyColumn(
               modifier = Modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.spacedBy(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ){
               item {
                   Column(
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(16.dp),
                       verticalArrangement = Arrangement.Center
                   ) {
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           color = MaterialTheme.colorScheme.primary,
                           fontSize = 28.sp,
                           text = "$greeting,"
                       )
                       Text(
                           modifier = Modifier.fillMaxWidth(),
                           color = MaterialTheme.colorScheme.primary,
                           fontSize = 38.sp,
                           text = "Stephen.",
                           fontStyle = FontStyle.Italic
                       )
                   }

               }

               item {
                   LazyRow(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.spacedBy(12.dp)
                   ){
                       items(mealCategories){ mealCategories->
                           SettingCard(
                               name = mealCategories.name,
                               icon = mealCategories.icon,
                               onClick = {}
                           )


                       }
                   }
               }

               item {
                   LazyRow(
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.spacedBy(12.dp)
                   ){
                       items(mealCategories){ mealCategories->
                           MyMealsCategoryItem(
                               category = mealCategories,
                               isSelected = { mealCategories.name == mealCategories.name.toString()},
                               onCategoryClick = {
                                   when(it){
                                       "Bus" -> {}
                                       "Flight" -> {}
                                       "Train" -> {}
                                   }
                               }
                           )


                       }
                   }
               }

               item {
                   Column {
                       Text(text = "Top Offers")

                       LazyRow(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.spacedBy(16.dp)
                       ){
                          items(5){
                              OfferCard()
                          }
                       }
                   }
               }
           }

       }
   }
}


@Composable
fun HomeToolbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 8.dp, end = 12.dp, bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.home_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Current Balance",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSecondaryContainer)
        )
    }
}


@Composable
fun OfferCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(120.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.85f)
        ){
            Image(
                painter = painterResource(id = R.drawable.welcom),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Bus")
            }
        }

    }
}


@Composable
fun SettingCard(onClick: (String) -> Unit, name: String, icon: Int) {
    Card(
        modifier = Modifier
            .size(80.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        onClick = { onClick(name) }
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(32.dp).padding(4.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary

            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.background
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyMealsCategoryItem(
    category: MealCategory,
    isSelected: (String) -> Boolean,
    onCategoryClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected(category.name)) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        onClick = {
            onCategoryClick(category.name)
        }
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(4.dp),
                painter = painterResource(id = category.icon),
                contentDescription = null,
                tint = if (isSelected(category.name)) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
            Text(
                text = category.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected(category.name)) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

data class MealCategory(
    val name: String,
    val icon: Int
)

private val mealCategories = listOf(
    MealCategory(
        "Bus",
        R.drawable.shuttle
    ),
    MealCategory(
        "Flight",
        R.drawable.shuttle
    ),
    MealCategory(
        "Train",
        R.drawable.shuttle
    ),
    MealCategory(
        "Hotel",
        R.drawable.shuttle
    ),

)

data class Category(
    val categoryId: String,
    val categoryName: String,
    val categoryDescription: String,
    val categoryImageUrl: String
)


@Composable
fun CategorySelection(state: CategoriesState, onClick: (String) -> Unit, selectedCategory: String) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(state.categories) { category ->
            CategoryItem(
                category = category, onClick = {
                    onClick(category.categoryName)
                }, selectedCategory = selectedCategory
            )
        }
    }
}

@Composable
fun CategoryItem(category: Category, selectedCategory: String, onClick: () -> Unit) {
    val selected = selectedCategory == category.categoryName
    Card(
        Modifier
            .width(100.dp)
            .wrapContentHeight()
            .clickable {
                onClick()
            }, shape = MaterialTheme.shapes.medium, colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp),
                contentDescription = null,
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = category.categoryImageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.home_logo)
                        }).build()
                ),
                contentScale = ContentScale.Inside
            )

            Text(
                text = category.categoryName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = if (selected) {
                    MaterialTheme.colorScheme.onPrimary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}

data class CategoriesState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val categories: List<Category> = emptyList()
)