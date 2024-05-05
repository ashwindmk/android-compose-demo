package com.ashwin.composedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ashwin.composedemo.model.Movie
import com.ashwin.composedemo.ui.screen.DetailScreen
import com.ashwin.composedemo.ui.screen.ListScreen
import com.ashwin.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            ComposeDemoTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Compose Demo",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            Modifier.shadow(8.dp)
                        )
                    }
                ) { paddingValues ->
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "ListScreen",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        val movies = getMovies()

                        composable(route = "ListScreen") {
                            ListScreen(movies = movies, navController = navController)
                        }

                        composable(
                            route = "DetailScreen/{index}",
                            arguments = listOf(
                                navArgument(
                                    name = "index",
                                    builder = {
                                        type = NavType.IntType
                                        nullable = false
                                        defaultValue = 1
                                    }
                                )
                            )
                        ) { navBackStackEntry ->
                            val index = navBackStackEntry.arguments?.getInt("index") ?: 0
                            Log.d("debug-log", "route = $route, index = $index clicked")
                            DetailScreen(movie = movies[index])
                        }
                    }
                }
            }
        }
    }
}

private fun getMovies(): List<Movie> {
    return listOf(
        Movie("Movie 1", R.drawable.movie1, "Lorem ipsum, ads for you, this movie is good."),
        Movie("Movie 2", R.drawable.movie2, "Lorem ipsum, ads for you, this movie is good."),
        Movie("Movie 3", R.drawable.movie3, "Lorem ipsum, ads for you, this movie is good."),
        Movie("Movie 4", R.drawable.movie4, "Lorem ipsum, ads for you, this movie is good."),
        Movie("Movie 5", R.drawable.movie5, "Lorem ipsum"),
        Movie("Movie 6", R.drawable.movie6, "Lorem ipsum, this movie is good."),
        Movie("Movie 7", R.drawable.movie7, "Lorem ipsum, ads for you, this movie is good."),
        Movie("Movie 8", R.drawable.movie8, "Lorem ipsum"),
        Movie("Movie 9", R.drawable.movie9, "Lorem ipsum"),
        Movie("Movie 10", R.drawable.movie10, "Lorem ipsum, ads for you, this movie is good.")
    )
}
