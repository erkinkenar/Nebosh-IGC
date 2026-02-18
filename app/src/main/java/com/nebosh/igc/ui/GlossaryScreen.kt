package com.nebosh.igc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebosh.igc.data.GlossaryTerm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlossaryScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()
    val allTerms by viewModel.glossaryTerms.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    // Arama Filtreleme Mantığı
    val filteredTerms = allTerms.filter { term ->
        // Hem ana terimde (İngilizce) hem de seçili dilin tanımında arama yapar
        val definitionToCheck = when (currentLang) {
            "tr" -> term.definitionTr
            "de" -> term.definitionDe
            "pl" -> term.definitionPl
            else -> term.definitionEn
        }

        // Arama sorgusu hem "Term" içinde hem de "Definition" içinde aranır
        term.term.contains(searchQuery, ignoreCase = true) ||
                definitionToCheck.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when(currentLang) {
                            "tr" -> "Sözlük"
                            "de" -> "Glossar"
                            "pl" -> "Słownik"
                            else -> "Glossary"
                        }
                    )
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
        ) {
            // ARAMA ÇUBUĞU
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        when(currentLang) {
                            "tr" -> "Ara..."
                            "de" -> "Suchen..."
                            "pl" -> "Szukaj..."
                            else -> "Search..."
                        }
                    )
                },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // LİSTE
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredTerms) { term ->
                    GlossaryCard(term, currentLang)
                }
            }
        }
    }
}

@Composable
fun GlossaryCard(term: GlossaryTerm, lang: String) {
    // Tanımı seçili dile göre al
    val definition = when (lang) {
        "tr" -> term.definitionTr
        "de" -> term.definitionDe
        "pl" -> term.definitionPl
        else -> term.definitionEn
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Ana Terim (Genelde İngilizce veya Uluslararası Terim)
            Text(
                text = term.term, // Veritabanındaki 'term' alanı
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color.LightGray.copy(alpha = 0.5f)
            )

            // Açıklama / Çeviri
            Text(
                text = definition,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}