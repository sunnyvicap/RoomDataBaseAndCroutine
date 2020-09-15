package com.sunny.shoppingzenex.Ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sunny.shoppingzenex.LocalData.LocalDatabase
import com.sunny.shoppingzenex.Model.LocalDao
import com.sunny.shoppingzenex.Model.LocalRepository
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private var productRepository: LocalRepository

    var products: LiveData<List<Products>>? = null

    var orders: LiveData<List<Orders>>? = null

    private var productsDao: LocalDao? = null

    init {

        productsDao = LocalDatabase.getDatabase(application).productDao()
        productRepository = LocalRepository(productsDao!!);


        products = productRepository.allProducts

        orders = productRepository.allOrders
    }

    fun getProduct(ids: List<String>): List<Products> {


        return productsDao!!.getProductById(ids)
    }

    fun update(products: List<Products>) = viewModelScope.launch(Dispatchers.IO) {


        for (product in products) {


            productRepository.update(product)


        }
    }

    fun insertOrder(order: Orders) = viewModelScope.launch(Dispatchers.IO) {


        productRepository.insertOrder(order)


    }

    fun insert() = viewModelScope.launch(Dispatchers.IO) {

        val product1 = Products(
            "Zara T-Shirt",
            1099,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product1);

        val product2 = Products(
            "Addidas T-Shirt",
            1599,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product2);


        val product3 = Products(
            "Nike T-Shirt",
            1499,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product3);

        val product4 = Products(
            "Puma T-Shirt",
            899,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product4);

        val product5 = Products(
            "Roadstar T-Shirt",
            499,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product5);


        val product6 = Products(
            "Wrong T-Shirt",
            499,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product6);


        val product7 = Products(
            "Jack&Jhones T-Shirt",
            599,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product7);


        val product8 = Products(
            "Levi's T-Shirt",
            499,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product8);


        val product9 = Products(
            "Denizen T-Shirt",
            659,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product9);

        val product10 = Products(
            "Blackberry T-Shirt",
            2199,
            "Essential Men´s T-shirts that bring out your personality"
        )

        productRepository.insert(product10);


    }


}