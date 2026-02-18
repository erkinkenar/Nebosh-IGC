package com.nebosh.igc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nebosh.igc.data.AppDatabase
import com.nebosh.igc.data.NeboshRepository
import com.nebosh.igc.ui.* // Tüm UI bileşenlerini import et (SplashScreen dahil)
import com.nebosh.igc.ui.theme.NeboshIGCTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Veritabanını ve Repository'i Başlat
        val database = AppDatabase.getDatabase(this)
        val repository = NeboshRepository(database.neboshDao())

        // 2. ViewModel'i Fabrika (Factory) ile Oluştur
        val viewModel: NeboshViewModel by viewModels {
            NeboshViewModelFactory(repository)
        }

        setContent {
            // ViewModel'den tema durumunu dinliyoruz (Karanlık/Aydınlık)
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            // Tüm uygulama bu temanın içinde olmalı
            NeboshIGCTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NeboshAppNavigation(viewModel = viewModel)
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
fun NeboshAppNavigation(viewModel: NeboshViewModel) {
    val navController = rememberNavController()

    // Başlangıç noktası Splash olarak ayarlandı
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // --- EKLENEN KISIM: SPLASH EKRANI ---
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        // ------------------------------------

        // 1. Ana Ekran
        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }

        // 2. Ayarlar
        composable(Screen.Settings.route) {
            SettingsScreen(viewModel = viewModel, navController = navController)
        }

        // 3. Risk Hesaplayıcı (ToolsScreen)
        composable(Screen.RiskCalculator.route) {
            ToolsScreen(viewModel = viewModel, navController = navController)
        }

        // 4. Konu Listesi
        composable(Screen.Topics.route) { backStackEntry ->
            val elementId = backStackEntry.arguments?.getString("elementId") ?: "IG1"
            ElementListScreen(
                courseId = elementId,
                navController = navController,
                viewModel = viewModel
            )
        }

        // 5. Alt Konular
        composable(Screen.SubTopics.route) { backStackEntry ->
            val chapterId = backStackEntry.arguments?.getString("chapterId") ?: "1"
            SubTopicScreen(
                chapterId = chapterId,
                viewModel = viewModel,
                navController = navController
            )
        }

        // 6. Sıralama Oyunu
        composable(Screen.OrderingGame.route) {
            OrderingGameScreen(viewModel = viewModel, navController = navController)
        }

        // 7. Sözlük
        composable(Screen.Glossary.route) {
            GlossaryScreen(viewModel = viewModel, navController = navController)
        }

        // 8. Ders Anlatımı
        composable(Screen.Flashcards.route) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: "1.1"
            LessonScreen(
                topicId = topicId,
                viewModel = viewModel,
                navController = navController
            )
        }

        // 9. Quiz
        composable(Screen.Quiz.route) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: "1.1"
            QuizScreen(
                topicId = topicId,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}