package com.sunny.shoppingzenex.Ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sunny.shoppingzenex.LocalData.LocalPrefrances

class SplashActivity : AppCompatActivity() {

    private lateinit var localPrefrances: LocalPrefrances

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        localPrefrances = LocalPrefrances(this);

        Thread.sleep(3000);

        if(localPrefrances.isLogout == false){


            val intent = Intent(this, HomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            startActivity(intent)
            finish()

        }else{


            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

            startActivity(intent)
            finish()
        }

    }
}