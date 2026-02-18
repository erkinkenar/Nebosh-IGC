package com.nebosh.igc.ui

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security // Veya VerifiedUser
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // Animasyon için ölçek (Scale) değeri: 0'dan başlayacak
    val scale = remember { Animatable(0f) }

    // Ekran açılınca çalışacak kod bloğu
    LaunchedEffect(key1 = true) {
        // 1. Animasyonu Başlat (0'dan 1'e büyü, biraz taşma efekti ver)
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )

        // 2. Biraz Bekle (2 saniye)
        delay(2000L)

        // 3. Ana Ekrana Git ve Splash'i Unut (Back Stack'ten sil)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    // --- GÖRSEL TASARIM ---
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary), // Arka plan: Ana Renk (Yeşil)
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Logo / İkon
            Icon(
                imageVector = Icons.Default.Security, // Buraya kendi ikonun da gelebilir
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value) // Animasyon burada uygulanıyor
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Uygulama Adı
            Text(
                text = "NEBOSH IGC Master",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.scale(scale.value)
            )
        }
    }
}