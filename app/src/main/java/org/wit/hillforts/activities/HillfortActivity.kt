package org.wit.hillforts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillforts.R
import org.wit.hillforts.main.MainApp
import org.wit.hillforts.models.HillfortModel

class HillfortActivity : AppCompatActivity(), AnkoLogger {

  var hillfort = HillfortModel()
  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort)
    app = application as MainApp

    btnAdd.setOnClickListener() {
      hillfort.title = hillfortTitle.text.toString()
      hillfort.description = hillfortDescription.text.toString()
      if (hillfort.title.isNotEmpty()) {
        app.hillforts.add(hillfort.copy())
        info("add button pressed: ${hillfort}")
        for (i in app.hillforts.indices) {
          info("hillfort[$i]: ${app.hillforts[i]}")
        }
      } else {
        toast ("Please enter a title")
      }
    }
  }
}
