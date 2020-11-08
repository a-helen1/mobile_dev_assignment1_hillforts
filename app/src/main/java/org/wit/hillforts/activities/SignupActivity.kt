package org.wit.hillforts.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.UserModel

class SignupActivity : AppCompatActivity(), AnkoLogger {

    var user = UserModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        app = application as MainApp

        btnRegister.setOnClickListener() {
            user.firstName = firstName.text.toString()
            user.lastName = lastName.text.toString()
            user.email = newEmail.text.toString()
            user.password= newPassword.text.toString()
            if (user.email.isEmpty() or user.password.isEmpty()) {
                toast("Please enter email and password details")
            } else {
                app.users.create(user.copy())
            }
            info("user added: ${user}")
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}