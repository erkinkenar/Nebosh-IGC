package com.nebosh.igc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RiskCalculatorScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()

    // State (Durum) Değişkenleri
    var likelihood by remember { mutableFloatStateOf(1f) }
    var severity by remember { mutableFloatStateOf(1f) }

    val score = (likelihood.roundToInt() * severity.roundToInt())

    // Risk Seviyesini Belirle
    val (riskLevel, riskColor, actionText) = calculateRisk(score, currentLang)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Risk Matrix Calculator") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. SONUÇ KARTI (Büyük Renkli Kutu)
            Card(
                colors = CardDefaults.cardColors(containerColor = riskColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "$score",
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = riskLevel.uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2. EYLEM GEREKLİLİĞİ
            Text(
                text = if (currentLang == "tr") "Eylem Gerekliliği:" else "Action Required:",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = actionText,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 3. AYARLAYICILAR (Sliders)

            // Olasılık (Likelihood)
            Text(
                text = getLabel("Likelihood", currentLang) + ": ${likelihood.roundToInt()}",
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = likelihood,
                onValueChange = { likelihood = it },
                valueRange = 1f..5f,
                steps = 3, // 1 ile 5 arası 3 adım (2,3,4)
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = getLikelihoodDesc(likelihood.roundToInt(), currentLang),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Şiddet (Severity)
            Text(
                text = getLabel("Severity", currentLang) + ": ${severity.roundToInt()}",
                fontWeight = FontWeight.Bold
            )
            Slider(
                value = severity,
                onValueChange = { severity = it },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = getSeverityDesc(severity.roundToInt(), currentLang),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

// --- YARDIMCI FONKSİYONLAR ---

fun calculateRisk(score: Int, lang: String): Triple<String, Color, String> {
    return when {
        score >= 16 -> Triple(
            if (lang == "tr") "ÇOK YÜKSEK" else "VERY HIGH",
            Color(0xFFD32F2F), // Koyu Kırmızı
            if (lang == "tr") "İşi derhal durdurun. Acil önlem şart."
            else "Stop work immediately. Immediate action required."
        )
        score >= 10 -> Triple(
            if (lang == "tr") "YÜKSEK" else "HIGH",
            Color(0xFFF57C00), // Turuncu
            if (lang == "tr") "İşi başlatmayın. Kısa vadede iyileştirme yapın."
            else "Do not start work. Improve within short timescale."
        )
        score >= 5 -> Triple(
            if (lang == "tr") "ORTA" else "MEDIUM",
            Color(0xFFFBC02D), // Sarı
            if (lang == "tr") "Belirli bir sürede önlem alınmalı."
            else "Action required within defined timescale."
        )
        else -> Triple(
            if (lang == "tr") "DÜŞÜK" else "LOW",
            Color(0xFF388E3C), // Yeşil
            if (lang == "tr") "Mevcut kontroller yeterli olabilir. İzleyin."
            else "Existing controls may be adequate. Monitor."
        )
    }
}

fun getLabel(type: String, lang: String): String {
    return if (lang == "tr") {
        if (type == "Likelihood") "Olasılık" else "Şiddet"
    } else {
        type
    }
}

fun getLikelihoodDesc(value: Int, lang: String): String {
    val en = listOf("", "Very Unlikely", "Unlikely", "Possible", "Likely", "Very Likely")
    val tr = listOf("", "Çok Düşük İhtimal", "Düşük İhtimal", "Mümkün", "Muhtemel", "Çok Muhtemel")
    return if (lang == "tr") tr.getOrElse(value) { "" } else en.getOrElse(value) { "" }
}

fun getSeverityDesc(value: Int, lang: String): String {
    val en = listOf("", "Minor Injury", "First Aid", "Lost Time Injury", "Major Injury", "Fatality")
    val tr = listOf("", "Küçük Sıyrık", "İlk Yardım", "İş Gücü Kaybı", "Ciddi Yaralanma", "Ölüm")
    return if (lang == "tr") tr.getOrElse(value) { "" } else en.getOrElse(value) { "" }
}