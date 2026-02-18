package com.nebosh.igc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebosh.igc.data.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubTopicScreen(
    chapterId: String,
    viewModel: NeboshViewModel,
    navController: NavController
) {
    // 1. KURS (IG1/IG2/IG2_PRATIK) BELİRLEME
    val elementNumber = chapterId.toIntOrNull() ?: 1
    val courseId = when {
        elementNumber == 12 -> "IG2_PRATIK"
        elementNumber >= 5 -> "IG2"
        else -> "IG1"
    }

    // 2. VERİLERİ ÇEKME
    val allTopicsState = viewModel.getTopicsByElement(courseId).collectAsState(initial = emptyList())
    val allTopics = allTopicsState.value

    // 3. DİL AYARI
    val currentLangState = viewModel.currentLanguage.collectAsState()
    val currentLang = currentLangState.value

    // 4. FİLTRELEME (Sadece 12.1, 12.2 vb. getir)
    val filteredTopics = allTopics.filter { topic ->
        topic.id.startsWith("$chapterId.")
    }

    val screenTitle = if(currentLang == "tr") "Element $chapterId Konuları" else "Element $chapterId Topics"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(screenTitle) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (filteredTopics.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = if(currentLang == "tr") "Konu bulunamadı ($courseId)" else "No topics found ($courseId)",
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredTopics) { topic ->
                        SubTopicCard(topic, currentLang, navController)
                    }
                }
            }
        }
    }
}

@Composable
fun SubTopicCard(topic: Topic, lang: String, navController: NavController) {
    val title = when(lang) {
        "tr" -> topic.title_tr
        "de" -> topic.title_de
        "pl" -> topic.title_pl
        else -> topic.title_en
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "${topic.id} $title",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate("flashcards/${topic.id}") },
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if(lang == "tr") "Ders" else "Lesson")
                }

                OutlinedButton(
                    onClick = { navController.navigate("quiz/${topic.id}") },
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Quiz")
                }
            }
        }
    }
}
