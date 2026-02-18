package com.nebosh.igc.ui

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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolsScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()

    // Değerleri tutan değişkenler (1.0 ile 5.0 arası)
    var likelihood by remember { mutableFloatStateOf(1f) }
    var severity by remember { mutableFloatStateOf(1f) }

    // Risk Puanı Hesaplama: Olasılık x Şiddet
    val score = (likelihood.toInt() * severity.toInt())

    // Dinamik renk ve metin belirleme
    val (riskColor, riskTextColor) = getRiskColor(score)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(when(currentLang) {
                        "tr" -> "Risk Hesaplayıcı (5x5)"
                        "de" -> "Risikorechner"
                        "pl" -> "Kalkulator Ryzyka"
                        else -> "Risk Calculator"
                    })
                },
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

            // --- BÖLÜM 1: GİRİŞLER ---
            // A) OLASILIK (LIKELIHOOD)
            Text(
                text = when(currentLang) {
                    "tr" -> "Olasılık (Likelihood)"
                    else -> "Likelihood"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            // Seçilen değerin açıklaması
            Text(
                text = "${likelihood.toInt()} - ${getLikelihoodLabel(likelihood.toInt(), currentLang)}",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = likelihood,
                onValueChange = { likelihood = it },
                valueRange = 1f..5f,
                steps = 3, // 1 ve 5 arası 3 adım (2,3,4)
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // B) ŞİDDET (SEVERITY)
            Text(
                text = when(currentLang) {
                    "tr" -> "Şiddet (Severity)"
                    else -> "Severity"
                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${severity.toInt()} - ${getSeverityLabel(severity.toInt(), currentLang)}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
            Slider(
                value = severity,
                onValueChange = { severity = it },
                valueRange = 1f..5f,
                steps = 3,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- BÖLÜM 2: SONUÇ KARTI ---

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = riskColor),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = when(currentLang) {
                            "tr" -> "RİSK PUANI"
                            else -> "RISK SCORE"
                        },
                        color = riskTextColor,
                        style = MaterialTheme.typography.labelLarge
                    )

                    // Büyük Puan Yazısı
                    Text(
                        text = "$score",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = riskTextColor
                    )

                    // Risk Seviyesi (Yüksek/Orta/Düşük)
                    Text(
                        text = getRiskLevelLabel(score, currentLang),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = riskTextColor
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = riskTextColor.copy(alpha = 0.5f)
                    )

                    // Yapılması Gereken Eylem
                    Text(
                        text = getRiskActionLabel(score, currentLang),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        color = riskTextColor
                    )
                }
            }
        }
    }
}

// --- YARDIMCI FONKSİYONLAR (Çeviriler ve Mantık) ---

fun getLikelihoodLabel(value: Int, lang: String): String {
    return when(value) {
        1 -> if(lang=="tr") "Çok Düşük İhtimal" else "Very Unlikely"
        2 -> if(lang=="tr") "Düşük İhtimal" else "Unlikely"
        3 -> if(lang=="tr") "Mümkün / Olabilir" else "Possible"
        4 -> if(lang=="tr") "Yüksek İhtimal" else "Likely"
        5 -> if(lang=="tr") "Çok Yüksek / Kesin" else "Very Likely"
        else -> ""
    }
}

fun getSeverityLabel(value: Int, lang: String): String {
    return when(value) {
        1 -> if(lang=="tr") "Küçük Sıyrık (İlk Yardım)" else "Minor Injury (First Aid)"
        2 -> if(lang=="tr") "Tıbbi Müdahale / Küçük İş Kaybı" else "Medical Treatment"
        3 -> if(lang=="tr") "İş Göremezlik (>7 Gün)" else "Lost Time Injury (>7 Days)"
        4 -> if(lang=="tr") "Büyük Yaralanma / Kalıcı Hasar" else "Major Injury"
        5 -> if(lang=="tr") "Ölüm (Fatality)" else "Fatality"
        else -> ""
    }
}

fun getRiskLevelLabel(score: Int, lang: String): String {
    return when {
        score >= 15 -> if(lang=="tr") "YÜKSEK RİSK 🚨" else "HIGH RISK 🚨"
        score >= 8 -> if(lang=="tr") "ORTA RİSK ⚠️" else "MEDIUM RISK ⚠️"
        else -> if(lang=="tr") "DÜŞÜK RİSK ✅" else "LOW RISK ✅"
    }
}

fun getRiskActionLabel(score: Int, lang: String): String {
    return when {
        score >= 15 -> if(lang=="tr") "İş DERHAL durdurulmalı. Risk azaltılmadan çalışılamaz." else "Stop work IMMEDIATELY. Immediate action required."
        score >= 8 -> if(lang=="tr") "Belirli bir takvim içinde önlem alınmalı." else "Action required within a defined time scale."
        else -> if(lang=="tr") "Mevcut önlemlerle izlemeye devam edin." else "Monitor with existing controls."
    }
}

// Skora göre Arka Plan ve Yazı Rengini döndürür
fun getRiskColor(score: Int): Pair<Color, Color> {
    return when {
        score >= 15 -> Color(0xFFD32F2F) to Color.White // Kırmızı (Yüksek) - Beyaz Yazı
        score >= 8 -> Color(0xFFFBC02D) to Color.Black // Sarı (Orta) - Siyah Yazı
        else -> Color(0xFF388E3C) to Color.White // Yeşil (Düşük) - Beyaz Yazı
    }
}