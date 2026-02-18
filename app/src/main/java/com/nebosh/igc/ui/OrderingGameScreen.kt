package com.nebosh.igc.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Collections

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderingGameScreen(
    viewModel: NeboshViewModel,
    navController: NavController
) {
    val currentLang by viewModel.currentLanguage.collectAsState()

    // OYUN VERİSİ
    val correctOrderEn = listOf(
        "1. Elimination (Most Effective)",
        "2. Substitution",
        "3. Engineering Controls",
        "4. Administrative Controls",
        "5. PPE (Least Effective)"
    )

    val correctOrderTr = listOf(
        "1. Ortadan Kaldırma (En Etkili)",
        "2. İkame (Yerine Koyma)",
        "3. Mühendislik Kontrolleri",
        "4. İdari Kontroller",
        "5. KKD (En Az Etkili)"
    )

    val targetList = if (currentLang == "tr") correctOrderTr else correctOrderEn
    var currentList by remember { mutableStateOf(targetList.shuffled()) }
    var isChecked by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (currentLang == "tr") "Sıralama Oyunu" else "Ordering Game") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        currentList = targetList.shuffled()
                        isChecked = false
                        isCorrect = false
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reset")
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text(
                    text = if (currentLang == "tr")
                        "Kontrol Hiyerarşisini en etkiliden (üstte) en aza (altta) doğru sıralayın."
                    else "Order the Hierarchy of Control from Most Effective (top) to Least Effective (bottom).",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(currentList) { index, item ->
                    OrderingItem(
                        text = item,
                        index = index,
                        listSize = currentList.size,
                        isGameFinished = isChecked,
                        isItemCorrect = isChecked && item == targetList[index],
                        onMoveUp = {
                            if (index > 0) {
                                val mutable = currentList.toMutableList()
                                Collections.swap(mutable, index, index - 1)
                                currentList = mutable
                                isChecked = false
                            }
                        },
                        onMoveDown = {
                            if (index < currentList.size - 1) {
                                val mutable = currentList.toMutableList()
                                Collections.swap(mutable, index, index + 1)
                                currentList = mutable
                                isChecked = false
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    isChecked = true
                    isCorrect = currentList == targetList
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isChecked && isCorrect) Color(0xFF4CAF50) else MaterialTheme.colorScheme.primary
                )
            ) {
                if (isChecked && isCorrect) {
                    Icon(Icons.Default.Check, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (currentLang == "tr") "TEBRİKLER!" else "CORRECT!")
                } else {
                    Text(if (currentLang == "tr") "Kontrol Et" else "Check Order")
                }
            }
        }
    }
}

@Composable
fun OrderingItem(
    text: String,
    index: Int,
    listSize: Int,
    isGameFinished: Boolean,
    isItemCorrect: Boolean,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit
) {
    val borderColor = if (isGameFinished) {
        if (isItemCorrect) Color.Green else Color.Red
    } else Color.Transparent

    val borderWidth = if (isGameFinished) 2.dp else 0.dp

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(borderWidth, borderColor, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text.replace(Regex("^\\d+\\.\\s"), ""),
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                fontWeight = FontWeight.Bold
            )

            // OK TUŞLARI YERİNE GARANTİ ÇALIŞAN METİN SEMBOLLERİ
            Column {
                IconButton(onClick = onMoveUp, enabled = index > 0 && !isGameFinished) {
                    // İkon yerine Metin: ▲
                    Text("▲", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = if (index > 0) MaterialTheme.colorScheme.primary else Color.LightGray)
                }
                IconButton(onClick = onMoveDown, enabled = index < listSize - 1 && !isGameFinished) {
                    // İkon yerine Metin: ▼
                    Text("▼", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = if (index < listSize - 1) MaterialTheme.colorScheme.primary else Color.LightGray)
                }
            }
        }
    }
}