package com.nebosh.igc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()

    var showLanguageDialog by remember { mutableStateOf(false) }

    val titleText = when(currentLang) { "tr" -> "Ayarlar"; "de" -> "Einstellungen"; else -> "Settings" }
    val appearanceTitle = when(currentLang) { "tr" -> "Görünüm"; "de" -> "Erscheinung"; else -> "Appearance" }
    val languageTitle = when(currentLang) { "tr" -> "Dil"; "de" -> "Sprache"; else -> "Language" }
    val darkModeText = when(currentLang) { "tr" -> "Karanlık Mod"; "de" -> "Dunkelmodus"; else -> "Dark Mode" }
    val languageSelectText = when(currentLang) { "tr" -> "Uygulama Dili"; "de" -> "App-Sprache"; else -> "App Language" }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLang = currentLang,
            onDismiss = { showLanguageDialog = false },
            onLanguageSelected = { langCode ->
                viewModel.changeLanguage(langCode)
                showLanguageDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(titleText) },
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
            // --- BÖLÜM 1: GÖRÜNÜM ---
            SettingsSectionTitle(appearanceTitle)

            ListItem(
                headlineContent = { Text(darkModeText) },
                leadingContent = { Icon(Icons.Default.DarkMode, contentDescription = null) },
                trailingContent = {
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { viewModel.toggleTheme(it) }
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // --- BÖLÜM 2: DİL ---
            SettingsSectionTitle(languageTitle)

            ListItem(
                headlineContent = { Text(languageSelectText) },
                supportingContent = {
                    Text(getLanguageName(currentLang), color = MaterialTheme.colorScheme.primary)
                },
                leadingContent = { Icon(Icons.Default.Language, contentDescription = null) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showLanguageDialog = true }
            )
        }
    }
}

@Composable
fun SettingsSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun LanguageSelectionDialog(
    currentLang: String,
    onDismiss: () -> Unit,
    onLanguageSelected: (String) -> Unit
) {
    val languages = listOf(
        "tr" to "Türkçe",
        "en" to "English",
        "de" to "Deutsch",
        "pl" to "Polski"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Dil Seçin / Select Language") },
        text = {
            Column {
                languages.forEach { (code, name) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLanguageSelected(code) }
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (code == currentLang),
                            onClick = { onLanguageSelected(code) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = name, fontSize = 16.sp)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("İptal / Cancel") }
        }
    )
}

fun getLanguageName(code: String): String {
    return when(code) {
        "tr" -> "Türkçe"
        "en" -> "English"
        "de" -> "Deutsch"
        "pl" -> "Polski"
        else -> "English"
    }
}