package org.wit.hillforts.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel



class LoginActivity : AppCompatActivity(), AnkoLogger {


    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        app = application as MainApp

        val users = app.users.findAll()

        login.setOnClickListener() {
            val email = email.text.toString()
            val password = password.text.toString()
            users.forEach {
                if (email.isEmpty() or password.isEmpty()) {
                    toast("Please enter login details")
                } else {
                    if (email.equals(email) && password.equals(password)) {
                        val i = Intent(this, HillfortListActivity::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        toast("Unknown Email or Password")
                    }
                }
            }


        }

        signup.setOnClickListener() {
            val i = Intent(this, SignupActivity::class.java)
            startActivity(i)
        }
    }
}