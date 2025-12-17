package com.app.wawacred_grupo2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CredControl::class, Vaccine::class, IronDose::class], version = 1, exportSchema = false)
_ABSTRACT_ class AppDatabase : RoomDatabase() {
    abstract fun credControlDao(): CredControlDao
    abstract fun vaccineDao(): VaccineDao
    abstract fun ironDoseDao(): IronDoseDao
}
