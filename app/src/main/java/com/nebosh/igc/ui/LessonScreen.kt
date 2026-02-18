package com.nebosh.igc.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.nebosh.igc.data.TopicContent
import com.nebosh.igc.data.LessonSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    topicId: String,
    viewModel: NeboshViewModel,
    navController: NavController
) {
    var topicContent by remember { mutableStateOf<TopicContent?>(null) }
    var currentPage by remember { mutableIntStateOf(0) }
    val currentLang by viewModel.currentLanguage.collectAsState()

    LaunchedEffect(topicId) {
        val topic = viewModel.getTopicById(topicId)
        if (topic != null && topic.contentJson.isNotEmpty()) {
            try {
                topicContent = Gson().fromJson(topic.contentJson, TopicContent::class.java)
            } catch (e: Exception) { }
        }
    }

    val pageTitle = when(currentLang) {
        "tr" -> "Konu Anlatımı"
        "de" -> "Vorlesungsnotizen"
        "pl" -> "Notatki z Wykładu"
        else -> "Lecture Notes"
    }

    val lessonPrefix = when(currentLang) {
        "tr" -> "Ders"
        "de" -> "Lektion"
        "pl" -> "Lekcja"
        else -> "Lesson"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(pageTitle, style = MaterialTheme.typography.titleMedium)
                        if (topicContent != null) {
                            Text(
                                text = "$lessonPrefix $topicId (${currentPage + 1}/${topicContent!!.sections.size})",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                        }
                    }
                },
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
        },
        bottomBar = {
            if (topicContent != null) {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Button(
                        onClick = { if (currentPage > 0) currentPage-- },
                        enabled = currentPage > 0,
                        modifier = Modifier.weight(1f).padding(8.dp)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if(currentLang == "tr") "Geri" else "Prev")
                    }

                    Button(
                        onClick = { if (currentPage < topicContent!!.sections.size - 1) currentPage++ },
                        enabled = currentPage < topicContent!!.sections.size - 1,
                        modifier = Modifier.weight(1f).padding(8.dp)
                    ) {
                        Text(if(currentLang == "tr") "İleri" else "Next")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    }
                }
            }
        }
    ) { innerPadding ->
        if (topicContent != null && topicContent!!.sections.isNotEmpty()) {
            val currentSection = topicContent!!.sections[currentPage]

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // GÖRSEL ALANI
                LessonImagePlaceholder(currentSection.imageType)

                Spacer(modifier = Modifier.height(24.dp))

                // BAŞLIK
                Text(
                    text = getText(currentSection.title, currentLang),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))

                // İÇERİK
                Text(
                    text = getText(currentSection.content, currentLang),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 28.sp,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Start
                )
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

fun getText(localized: com.nebosh.igc.data.LocalizedText, lang: String): String {
    return when(lang) {
        "tr" -> localized.tr
        "de" -> localized.de
        "pl" -> localized.pl
        else -> localized.en
    }
}

@Composable
fun LessonImagePlaceholder(imageType: String) {
    val icon: ImageVector = when(imageType) {
        "pdca" -> Icons.Default.Refresh // PUKÖ döngüsü için (Döngü ikonu)
        "policy" -> Icons.Default.List  // Politika için (Liste ikonu - MenuBook yerine)
        "hazard" -> Icons.Default.Warning // Tehlike için (Ünlem)
        "audit" -> Icons.Default.Settings // Denetim için (Çark - Construction yerine)
        else -> Icons.Default.Info      // Varsayılan (Bilgi)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}
