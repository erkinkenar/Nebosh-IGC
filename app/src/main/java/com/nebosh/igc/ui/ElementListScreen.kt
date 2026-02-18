package com.nebosh.igc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Element Bilgisi İçin Veri Modeli
data class ElementMeta(val id: String, val titleEn: String, val titleTr: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElementListScreen(
    courseId: String, // "IG1", "IG2" veya "IG2_PRATIK"
    navController: NavController,
    viewModel: NeboshViewModel
) {
    val currentLang by viewModel.currentLanguage.collectAsState()

    // IG1 MÜFREDATI
    val ig1Elements = listOf(
        ElementMeta("1", "Why we should manage workplace health and safety", "İşyerinde Sağlık ve Güvenliği Neden Yönetmeliyiz"),
        ElementMeta("2", "How health and safety management systems work", "İSG Yönetim Sistemleri Nasıl Çalışır"),
        ElementMeta("3", "Managing risk – understanding people and processes", "Risk Yönetimi – İnsanları ve Süreçleri Anlama"),
        ElementMeta("4", "Health and safety monitoring and measuring", "İSG İzleme ve Ölçme")
    )

    // IG2 MÜFREDATI
    val ig2Elements = listOf(
        ElementMeta("5", "Physical and psychological health", "Fiziksel ve Psikolojik Sağlık"),
        ElementMeta("6", "Musculoskeletal health", "Kas ve İskelet Sağlığı"),
        ElementMeta("7", "Chemical and biological agents", "Kimyasal ve Biyolojik Ajanlar"),
        ElementMeta("8", "General workplace issues", "Genel İşyeri Sorunları"),
        ElementMeta("9", "Work equipment", "İş Ekipmanları"),
        ElementMeta("10", "Fire", "Yangın"),
        ElementMeta("11", "Electricity", "Elektrik")
    )

    // IG2 PRATİK
    val ig2PratikElements = listOf(
        ElementMeta("12", "Practical risk assessment", "Pratik Risk Değerlendirmesi")
    )

    // Hangi listeyi göstereceğimize karar verelim
    val elementsToShow = when (courseId) {
        "IG2" -> ig2Elements
        "IG2_PRATIK" -> ig2PratikElements
        else -> ig1Elements
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if(currentLang == "tr") "Bölümler ($courseId)" else "Elements ($courseId)") },
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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(elementsToShow) { element ->
                ElementCard(element, currentLang, navController)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ElementCard(element: ElementMeta, lang: String, navController: NavController) {
    val title = if (lang == "tr") element.titleTr else element.titleEn

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                navController.navigate("subtopics/${element.id}")
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = element.id,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if(lang == "tr") "Element ${element.id}" else "Element ${element.id}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2
                )
            }

            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                modifier = Modifier.rotate(180f)
            )
        }
    }
}
