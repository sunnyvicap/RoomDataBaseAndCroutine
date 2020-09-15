package com.sunny.shoppingzenex.Ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.sunny.shoppingzenex.LocalData.LocalPrefrances
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.fragment.CartFragment
import com.sunny.shoppingzenex.Ui.fragment.HomeFragment
import com.sunny.shoppingzenex.Ui.fragment.MerchantFragment
import com.sunny.shoppingzenex.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var hb :ActivityHomeBinding

    private lateinit var  localPrefrances:LocalPrefrances;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hb = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(hb.root)
        localPrefrances = LocalPrefrances(this)

        setToolbar();

        val fragmentManager = supportFragmentManager.beginTransaction()
        if(localPrefrances.userLogin !=null  && localPrefrances.userLogin == true){

            fragmentManager.add(R.id.homeContainer ,HomeFragment(), "HOME")
            fragmentManager.addToBackStack(null)
            fragmentManager.commitAllowingStateLoss()

        }else{

            fragmentManager.add(R.id.homeContainer ,MerchantFragment(), "MERCHANT")
            fragmentManager.addToBackStack(null)
            fragmentManager.commitAllowingStateLoss()


        }


    }

    private fun setToolbar(){

        hb.topToolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.logOut -> {

                    localPrefrances.reset(this)

                    val  intent  = Intent(this, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()

                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val  fragmentManager = supportFragmentManager
        val currentInstance = fragmentManager.findFragmentById(R.id.homeContainer)

        if(currentInstance is HomeFragment || currentInstance is
                MerchantFragment){

            finish();
        }
    }
}