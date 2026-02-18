package com.nebosh.igc.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nebosh.igc.data.SyllabusElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val elements by viewModel.syllabusElements.collectAsState()
    val currentLang by viewModel.currentLanguage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NEBOSH IGC") },
                actions = {
                    // YENİ: Sözlük Butonu (Liste İkonu)
                    IconButton(onClick = { navController.navigate(Screen.Glossary.route) }) {
                        Icon(Icons.Default.List, contentDescription = "Glossary")
                    }
                    // Ayarlar Butonu
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Screen.RiskCalculator.route) },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) {
                Icon(Icons.Default.Add, contentDescription = "Risk Calc")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = if (currentLang == "tr") "Risk Matrisi" else "Risk Matrix")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(elements) { element ->
                SyllabusCard(element = element, currentLang = currentLang) {
                    navController.navigate(Screen.Topics.createRoute(element.id))
                }
            }
        }
    }
}

@Composable
fun SyllabusCard(
    element: SyllabusElement,
    currentLang: String,
    onClick: () -> Unit
) {
    val (title, description) = when (currentLang) {
        "tr" -> element.title_tr to element.description_tr
        "de" -> element.title_de to element.description_de
        "pl" -> element.title_pl to element.description_pl
        else -> element.title_en to element.description_en
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text(
                text = element.id,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}