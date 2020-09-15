package com.sunny.shoppingzenex.Model

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sunny.shoppingzenex.LocalData.LocalDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private class ProductsCallBack(val coroutineScope: CoroutineScope) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        LocalDatabase.INSTANCE?.let {
            coroutineScope.launch {
                populateDatabase(it.productDao())
            }
        }
    }

    private suspend fun populateDatabase(productDao: LocalDao) {



    }
}