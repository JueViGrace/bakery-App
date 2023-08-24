package com.bakery.bakeryapp.data.local.dao.categories

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.bakery.bakeryapp.data.local.entities.categories.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Upsert
    suspend fun upsertCategories(category: List<CategoryEntity>)

    @Query("DELETE FROM categories")
    suspend fun deleteCategories()

    @Query("SELECT * FROM categories")
    fun getCategories(): Flow<List<CategoryEntity>>
}