package hu.bme.aut.bertokattila.favcats.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import hu.bme.aut.bertokattila.favcats.models.StoredCat

@Dao
interface StoredCatDao {
    @Query("SELECT * FROM StoredCat")
    fun getAll(): List<StoredCat>

    @Insert
    fun insertAll(vararg cats: StoredCat?)

    @Delete
    fun delete(user: StoredCat)
}