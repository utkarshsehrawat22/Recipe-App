@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.screens.components.ErrorMessage
import com.example.presentation.screens.components.LoadingIndicator
import com.example.presentation.viewmodel.homeViewModel

@Composable
fun HomeScreen(
    onRecipeclick: (Int) -> Unit,
    viewModel: homeViewModel = viewModel(),
) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "Recipe App",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(start = 4.dp))
                        Text(
                            text = "By Utkarsh Sehrawat",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                windowInsets = WindowInsets.statusBars
            )
        }
    ) { innerpadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
        ) {
            when {
                viewModel.loading -> LoadingIndicator()

                viewModel.errormessaging != null -> {
                    ErrorMessage(
                        message = viewModel.errormessaging!!
                    ) { viewModel.fetchRecipe() }
                }

                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(bottom = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            HomeHeader()
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            CategorySection(
                                categories = viewModel.categories,
                                selected = viewModel.selectedcategories,
                                onSelected = viewModel::oncategorySelected
                            )
                        }

                        item(span = { GridItemSpan(maxLineSpan) }) {
                            SectionHeader(
                                title = if (viewModel.selectedcategories == "All") "All Recipes" else "${viewModel.selectedcategories} Recipes",
                                icon = Icons.AutoMirrored.Filled.MenuOpen
                            )
                        }

                        if (viewModel.recipes.isEmpty()) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "No Recipes found", color = Color.Gray)
                                }
                            }
                        } else {
                            items(viewModel.recipes, key = { it.id }) { recipe ->
                                Recipecard(
                                    recipe = recipe,
                                    onClick = { onRecipeclick(recipe.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
