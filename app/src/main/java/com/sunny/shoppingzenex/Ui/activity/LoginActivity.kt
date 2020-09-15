package com.sunny.shoppingzenex.Ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunny.shoppingzenex.LocalData.LocalPrefrances
import com.sunny.shoppingzenex.Util.toast
import com.sunny.shoppingzenex.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var bb: ActivityLoginBinding

    private lateinit var localPrefrances: LocalPrefrances

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bb.root)

        localPrefrances = LocalPrefrances(this)
        bb.mButtonSignIn.setOnClickListener {

            loginUser();

        }
    }

    private fun loginUser() {

        val strUserName = bb.mEditTextEmail.text.toString().trim() { it <= ' ' };
        val strPassword = bb.mEditTextPassword.text.toString().trim() { it <= ' ' };

        if (strUserName.isEmpty()) {

            bb.mEditInputFieldEmail.requestFocus()
            bb.mEditInputFieldEmail.error = "Please Enter User Name *"

        } else if (strPassword.isEmpty() && strPassword.length < 8) {

            bb.mEditInputFieldPassword.requestFocus()
            bb.mEditInputFieldPassword.error = "Password Should Be Minimum 8 Characters"

        } else {

            if (strUserName.contentEquals("User") && strPassword.contentEquals("12345")) {



                localPrefrances.userLogin = true
                localPrefrances.merchantLogin = false

                localPrefrances.isLogout = false

                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
                finish()

            } else if (strUserName.contentEquals("Merchant") && strPassword.contentEquals("12345")) {



                localPrefrances.userLogin = false
                localPrefrances.merchantLogin = true

                localPrefrances.isLogout = false

                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

                startActivity(intent)
                finish()

            } else {
                toast("User Name and Password is not valid")
            }



        }

    }
}