package com.nebosh.igc

import android.app.Application
import com.nebosh.igc.data.AppDatabase
import com.nebosh.igc.data.NeboshRepository

class NeboshApplication : Application() {
    // Veritabanı ve Repository sadece ihtiyaç duyulduğunda oluşturulur
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { NeboshRepository(database.neboshDao()) }
}