package com.nebosh.igc.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nebosh.igc.data.GlossaryTerm
import com.nebosh.igc.data.NeboshRepository
import com.nebosh.igc.data.Question
import com.nebosh.igc.data.SyllabusElement
import com.nebosh.igc.data.Topic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NeboshViewModel(private val repository: NeboshRepository) : ViewModel() {
    fun changeLanguage(langCode: String) {
        _currentLanguage.value = langCode
        // İstersen burada seçilen dili yerel hafızaya (DataStore/SharedPreferences) kaydedebilirsin.
    }
    // Karanlık Mod Durumu (Varsayılan: Kapalı/False)
    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()
    // Temayı değiştiren fonksiyon
    fun toggleTheme(isDark: Boolean) {
        _isDarkTheme.value = isDark
    }
    // Dil Durumu
    private val _currentLanguage = MutableStateFlow("en")
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    // Ana Üniteler
    private val _syllabusElements = MutableStateFlow<List<SyllabusElement>>(emptyList())
    val syllabusElements: StateFlow<List<SyllabusElement>> = _syllabusElements.asStateFlow()

    // Sözlük
    private val _glossaryTerms = MutableStateFlow<List<GlossaryTerm>>(emptyList())
    val glossaryTerms: StateFlow<List<GlossaryTerm>> = _glossaryTerms.asStateFlow()

    // Arama
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        insertSampleData()      // Veritabanını doldur
        loadSyllabusElements()  // Üniteleri yükle
        loadGlossary()          // Sözlüğü yükle
    }
    // Dili Değiştir
    fun setLanguage(langCode: String) {
        _currentLanguage.value = langCode
    }
    // Arama Metnini Güncelle
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
    private fun insertSampleData() {
        viewModelScope.launch {
            repository.insertSampleData()
        }
    }
    private fun loadSyllabusElements() {
        viewModelScope.launch {
            repository.allSyllabusElements.collect { elements ->
                _syllabusElements.value = elements
            }
        }
    }
    private fun loadGlossary() {
        viewModelScope.launch {
            repository.allGlossaryTerms.collect { terms ->
                _glossaryTerms.value = terms
            }
        }
    }
    fun getTopicsByElement(elementId: String): Flow<List<Topic>> {
        return repository.getTopicsByElement(elementId)
    }
    // Tek bir konuyu getir (Ders ekranı için)
    suspend fun getTopicById(topicId: String): Topic? {
        return repository.getTopicById(topicId)
    }
    // Konuya ait soruları getir (Quiz için)
    suspend fun getQuestionsByTopic(topicId: String): List<Question> {
        return repository.getQuestionsByTopic(topicId)
    }
}
class NeboshViewModelFactory(private val repository: NeboshRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NeboshViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NeboshViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}