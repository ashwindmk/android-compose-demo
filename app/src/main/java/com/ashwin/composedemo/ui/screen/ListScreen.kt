package com.ashwin.composedemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ashwin.composedemo.model.Movie

@Composable
fun ListScreen(
    movies: List<Movie>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movies.size) {
            MovieItem(it, movies[it], navController, modifier)
        }
    }
}

@Composable
fun MovieItem(
    index: Int,
    movie: Movie,
    navController: NavController,
    modifier: Modifier
) {
    Card(
        modifier
            .padding(8.dp)
            .wrapContentSize()
            .clickable {
                navController.navigate(route = "DetailScreen/${index}")
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Image(painter = painterResource(id = movie.image), contentDescription = movie.title, modifier.size(140.dp))
            Column(modifier.padding(12.dp)) {
                Text(text = movie.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(text = movie.description, fontSize = 16.sp)
            }
        }
    }
}
