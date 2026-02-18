package com.nebosh.igc.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nebosh.igc.data.LocalizedText
import com.nebosh.igc.data.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    topicId: String,
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()

    // Veritabanından gelen soruları tutacak liste
    var questionList by remember { mutableStateOf<List<Question>>(emptyList()) }
    // Şu an kaçıncı sorudayız?
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    // Ekran açılınca o konuya ait soruları getir
    LaunchedEffect(topicId) {
        questionList = viewModel.getQuestionsByTopic(topicId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(when(currentLang) {
                        "tr" -> "Sınav Soruları"
                        "de" -> "Prüfungsfragen"
                        "pl" -> "Pytania Egzaminacyjne"
                        else -> "Exam Questions"
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
                .verticalScroll(rememberScrollState())
        ) {
            if (questionList.isNotEmpty()) {
                val currentQuestion = questionList[currentQuestionIndex]

                // İlerleme Çubuğu (Örn: Soru 1/5)
                Text(
                    text = "${currentQuestionIndex + 1} / ${questionList.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.End)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Soru Kartı Bileşeni
                QuestionCard(
                    question = currentQuestion,
                    currentLang = currentLang,
                    onNextClicked = {
                        // Sonraki soruya geç (varsa)
                        if (currentQuestionIndex < questionList.size - 1) {
                            currentQuestionIndex++
                        } else {
                            // Sınav bitti, geri dön
                            navController.popBackStack()
                        }
                    },
                    isLastQuestion = currentQuestionIndex == questionList.size - 1
                )

            } else {
                // Soru Yoksa
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = when(currentLang) {
                            "tr" -> "Bu konu için henüz soru eklenmemiş."
                            else -> "No questions available for this topic yet."
                        },
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionCard(
    question: Question,
    currentLang: String,
    onNextClicked: () -> Unit,
    isLastQuestion: Boolean
) {
    // Şıkları JSON'dan listeye çeviriyoruz
    val optionsList: List<LocalizedText> = remember(question.options) {
        val type = object : TypeToken<List<LocalizedText>>() {}.type
        Gson().fromJson(question.options, type)
    }

    var selectedIndex by remember(question) { mutableStateOf<Int?>(null) }
    var isSubmitted by remember(question) { mutableStateOf(false) }

    // Soru Metni
    Text(
        text = getLocalizedText(question.question, currentLang),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Şıklar
    optionsList.forEachIndexed { index, optionText ->
        val isSelected = index == selectedIndex
        val isCorrectAnswer = index == question.correctAnswerIndex

        // Renk Mantığı
        val backgroundColor = when {
            isSubmitted && isCorrectAnswer -> Color(0xFF4CAF50) // Doğru cevap YEŞİL
            isSubmitted && isSelected && !isCorrectAnswer -> Color(0xFFE53935) // Yanlış seçim KIRMIZI
            isSelected -> MaterialTheme.colorScheme.primaryContainer // Seçili MAVİMSİ
            else -> MaterialTheme.colorScheme.surface
        }

        val borderStroke = if (isSelected || (isSubmitted && isCorrectAnswer))
            BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
        else
            BorderStroke(1.dp, Color.LightGray)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            border = borderStroke,
            onClick = {
                if (!isSubmitted) {
                    selectedIndex = index
                }
            }
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Şık Harfi (A, B, C, D)
                Text(
                    text = "${('A' + index)}.",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(30.dp)
                )
                // Şık Metni
                Text(
                    text = getLocalizedText(optionText, currentLang),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Butonlar ve Açıklama Alanı
    if (!isSubmitted) {
        Button(
            onClick = { isSubmitted = true },
            enabled = selectedIndex != null,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(if (currentLang == "tr") "Cevabı Kontrol Et" else "Check Answer")
        }
    } else {
        // Sonuç Açıklaması
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = if (currentLang == "tr") "AÇIKLAMA:" else "EXPLANATION:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = getLocalizedText(question.explanation, currentLang),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sonraki Soru Butonu
        Button(
            onClick = onNextClicked,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                if (isLastQuestion) {
                    if (currentLang == "tr") "Sınavı Bitir" else "Finish Quiz"
                } else {
                    if (currentLang == "tr") "Sonraki Soru" else "Next Question"
                }
            )
        }
    }
}

// Dil seçimine göre metni döndüren yardımcı fonksiyon
fun getLocalizedText(localizedText: LocalizedText, lang: String): String {
    return when (lang) {
        "tr" -> localizedText.tr
        "de" -> localizedText.de
        "pl" -> localizedText.pl
        else -> localizedText.en
    }
}
