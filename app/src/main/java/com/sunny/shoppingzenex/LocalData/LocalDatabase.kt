package com.sunny.shoppingzenex.LocalData

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.sunny.shoppingzenex.Model.LocalDao
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products

@Database(entities = [Products::class,Orders::class], version = 1,  exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){

    abstract fun productDao() : LocalDao


    companion object{

        @Volatile
        public var INSTANCE :LocalDatabase ?=null

        fun getDatabase(context: Context):LocalDatabase{

            val tempInstance = INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "Zenex"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }





    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {

        TODO("Not yet implemented")

    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {

    }

}