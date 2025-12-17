package com.app.wawacred_grupo2

import android.app.Application
import androidx.room.Room
import com.app.wawacred_grupo2.data.db.AppDatabase

class WawaCredApp : Application() {

    val database: AppDatabase by lazy { 
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "wawacred_database"
        ).build()
    }
}
