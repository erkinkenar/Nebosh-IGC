package com.nebosh.igc.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NeboshDao {

    // --- MÜFREDAT VE KONULAR ---
    @Query("SELECT * FROM syllabus_elements")
    fun getAllSyllabusElements(): Flow<List<SyllabusElement>>

    // Tablo adı 'topics' (Entities dosyasındaki ile uyumlu)
    @Query("SELECT * FROM topics WHERE elementId = :elementId")
    fun getTopicsByElement(elementId: String): Flow<List<Topic>>

    @Query("SELECT * FROM topics WHERE id = :topicId")
    suspend fun getTopicById(topicId: String): Topic?

    // --- EKLEME İŞLEMLERİ (ANA VERİ) ---
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSyllabusElement(element: SyllabusElement)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: Topic)

    // --- SİLME İŞLEMLERİ (ANA VERİ) ---
    @Query("DELETE FROM topics")
    suspend fun deleteAllTopics()

    @Query("DELETE FROM syllabus_elements")
    suspend fun deleteAllElements()

    // 1. SORULAR (QUESTIONS)
    @Query("SELECT * FROM questions WHERE topicId = :topicId")
    suspend fun getQuestionsByTopic(topicId: String): List<Question>

    // 2. SORU EKLE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    // 3. SORULARI SİL (Repository'deki hatayı bu çözer)
    @Query("DELETE FROM questions")
    suspend fun deleteAllQuestions()

    // 4. SÖZLÜK
    @Query("SELECT * FROM glossary")
    fun getAllTerms(): Flow<List<GlossaryTerm>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerm(term: GlossaryTerm)

    @Query("DELETE FROM glossary")
    suspend fun deleteAllTerms()
}