package com.sunny.shoppingzenex.Model

import androidx.lifecycle.LiveData
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products

class LocalRepository (private val productDao: LocalDao){

    val allProducts : LiveData<List<Products>> = productDao.getAllProducts()

    val allOrders : LiveData<List<Orders>> = productDao.getAllOrders();


    suspend fun insert(products: Products) {
        productDao.insert(products)
    }

    suspend fun insertOrder(orders: Orders){
        productDao.insertOrders(orders)
    }

    suspend fun update(products: Products) {
        productDao.update(products)
    }
}