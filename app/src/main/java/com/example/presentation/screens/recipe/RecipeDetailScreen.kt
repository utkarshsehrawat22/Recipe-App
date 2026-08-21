package com.example.presentation.screens.recipe

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.presentation.screens.components.ErrorMessage
import com.example.presentation.screens.components.LoadingIndicator
import com.example.presentation.screens.components.MyTopAppBar
import com.example.presentation.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    recipeID: Int,
    onBack: () -> Unit,
    viewModel: RecipeDetailViewModel = viewModel(),
) {
    LaunchedEffect(recipeID) {
        viewModel.fetchRecipeDetail(recipeID)
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            MyTopAppBar(
                title = "Recipe Details",
                onBackclick = onBack,
                icon = Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                viewModel.isloading -> LoadingIndicator()
                viewModel.errormessage != null -> {
                    ErrorMessage(
                        message = viewModel.errormessage!!
                    ) { viewModel.fetchRecipeDetail(recipeID) }
                }
                viewModel.recipe != null -> {
                    val recipe = viewModel.recipe!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        AsyncImage(
                            model = recipe.image,
                            contentDescription = recipe.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
                            contentScale = ContentScale.Crop
                        )
                        
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = recipe.name,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                InfoChip(icon = Icons.Default.Star, text = "${recipe.rating} (${recipe.reviewCount})", color = Color(0xFFFF8C00))
                                Spacer(modifier = Modifier.width(16.dp))
                                InfoChip(icon = Icons.Default.AccessTime, text = "${recipe.cookTimeMinutes} min", color = Color.Gray)
                                Spacer(modifier = Modifier.width(16.dp))
                                InfoChip(icon = Icons.Default.LocalFireDepartment, text = "${recipe.caloriesPerServing} kcal", color = Color.Gray)
                            }
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            Text(text = "Ingredients", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))
                            recipe.ingredients.forEach { ingredient ->
                                Text(
                                    text = "• $ingredient",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    color = Color.DarkGray
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(24.dp))
                            
                            Text(text = "Instructions", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(12.dp))
                            recipe.instructions.forEachIndexed { index, instruction ->
                                Row(modifier = Modifier.padding(vertical = 8.dp)) {
                                    Text(
                                        text = (index + 1).toString(),
                                        color = Color(0xFFFF8C00),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(24.dp)
                                    )
                                    Text(
                                        text = instruction,
                                        fontSize = 16.sp,
                                        color = Color.DarkGray
                                    )
                                }
                                if (index < (recipe.instructions.size - 1)) {
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), color = Color.LightGray.copy(alpha = 0.5f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoChip(icon: ImageVector, text: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, fontSize = 14.sp, color = color, fontWeight = FontWeight.Medium)
    }
}
