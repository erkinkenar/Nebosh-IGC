package com.nebosh.igc.data

// --- DERS İÇERİK MODELİ ---
data class TopicContent(
    val sections: List<LessonSection>
)

data class LessonSection(
    val title: LocalizedText,
    val content: LocalizedText,
    val imageType: String = "default" // YENİ: Resim türünü belirten alan
)

data class LocalizedText(
    val tr: String,
    val en: String,
    val de: String,
    val pl: String
)

// --- SINAV (QUIZ) MODELİ ---
data class QuestionContent(
    val tr: QuizData,
    val en: QuizData,
    val de: QuizData,
    val pl: QuizData
)

data class QuizData(
    val scenario: String,
    val stem: String,
    val options: List<QuizOption>,
    val explanation: String
)

data class QuizOption(
    val id: String,
    val text: String,
    val isCorrect: Boolean
)