package com.example.bancodados2024.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.bancodados2024.entities.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Insert
    fun insert(product: Product): Long

    @Update
    fun update(product: Product)

    @Upsert
    fun upsert(product: Product): Long

    @Query("select * from product p order by p.name")
    fun getAll(): Flow<List<Product>>

    @Query("select * from product p where p.id = :id")
    fun findById(id: Long) : Product?
}