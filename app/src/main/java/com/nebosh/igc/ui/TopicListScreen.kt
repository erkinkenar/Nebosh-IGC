package com.nebosh.igc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebosh.igc.data.Topic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicListScreen(
    elementId: String,
    viewModel: NeboshViewModel,
    navController: NavController
) {
    // Veritabanından gelen düz liste
    val topics by viewModel.getTopicsByElement(elementId).collectAsState(initial = emptyList())
    val currentLang by viewModel.currentLanguage.collectAsState()

    // LİSTEYİ GRUPLAMA MANTIĞI
    val groupedTopics = remember(topics) {
        topics.groupBy { topic ->
            topic.id.split(".").firstOrNull() ?: "Other"
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$elementId Topics") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentPadding = PaddingValues(16.dp)
        ) {
            groupedTopics.forEach { (chapterNum, chapterTopics) ->

                // 1. CHAPTER BAŞLIĞI
                item {
                    ChapterHeader(chapterNum, currentLang)
                }

                // 2. O CHAPTER'IN KONULARI
                items(chapterTopics) { topic ->
                    TopicCard(topic, currentLang, navController)
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }
        }
    }
}

@Composable
fun ChapterHeader(chapterNum: String, lang: String) {
    val title = when(lang) {
        "tr" -> "Element $chapterNum"
        else -> "Element $chapterNum"
    }

    // Alt başlıkları belirle
    val subTitle = when(chapterNum) {
        "1" -> if(lang=="tr") "Neden Yönetmeliyiz?" else "Why Manage H&S?"
        "2" -> if(lang=="tr") "Yönetim Sistemleri" else "Management Systems"
        "3" -> if(lang=="tr") "Risk Yönetimi" else "Managing Risk"
        else -> ""
    }

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        if(subTitle.isNotEmpty()){
            Text(
                text = subTitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 4.dp), color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    }
}

@Composable
fun TopicCard(topic: Topic, lang: String, navController: NavController) {
    // Dil seçimine göre başlığı al
    val title = when(lang) {
        "tr" -> topic.title_tr
        "de" -> topic.title_de
        "pl" -> topic.title_pl
        else -> topic.title_en
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
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
                // DERS BUTONU
                Button(
                    onClick = { navController.navigate("flashcards/${topic.id}") },
                    modifier = Modifier.weight(1f).padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if(lang == "tr") "Ders" else "Lesson")
                }

                // QUIZ BUTONU
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
