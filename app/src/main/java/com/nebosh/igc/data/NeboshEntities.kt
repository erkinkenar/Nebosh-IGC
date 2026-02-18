package com.nebosh.igc.data
import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Üniteler Tablosu
@Entity(tableName = "syllabus_elements")
data class SyllabusElement(
    @PrimaryKey val id: String,
    val title_tr: String,
    val title_en: String,
    val title_de: String,
    val title_pl: String,
    val description_tr: String,
    val description_en: String,
    val description_de: String,
    val description_pl: String
)
// 2. Konular Tablosu
@Entity(tableName = "topics")
data class Topic(
    @PrimaryKey val id: String,
    val elementId: String,
    val title_tr: String,
    val title_en: String,
    val title_de: String,
    val title_pl: String,
    val contentJson: String
)
// 3. Sorular Tablosu
// Entity dosyanın en altına ekleyebilirsin:

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey val id: String,
    val topicId: String,          // Hangi konuya ait?
    val question: LocalizedText,  // Soru metni
    val options: String,          // Şıklar (JSON olarak)
    val correctAnswerIndex: Int,  // Doğru şıkkın sırası (0, 1, 2...)
    val explanation: LocalizedText // Cevap açıklaması
)
@Entity(tableName = "glossary")
data class GlossaryTerm(
    @PrimaryKey val id: String,
    val term: String,
    val definitionTr: String,
    val definitionEn: String,
    val definitionDe: String,
    val definitionPl: String
)