package com.sunny.shoppingzenex.Model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products


@Dao
interface LocalDao {

    @Query("Select * from product")
    fun getAllProducts(): LiveData<List<Products>>

    @Query("select * from product where id in (:ids)")
    fun getProductById(ids:List<String>): List<Products>

    @Query("Select * from 'order'")
    fun getAllOrders(): LiveData<List<Orders>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: Products)

    @Update
    suspend fun update(products: Products)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: Orders)

}