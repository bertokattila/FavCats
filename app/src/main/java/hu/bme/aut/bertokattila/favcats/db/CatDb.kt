package hu.bme.aut.bertokattila.favcats.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.bertokattila.favcats.models.StoredCat

@Database(entities = [StoredCat::class], version = 1)
abstract class CatDb : RoomDatabase() {
    abstract fun catDao(): StoredCatDao
}