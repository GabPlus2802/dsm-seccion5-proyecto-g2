package com.app.wawacred_grupo2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectLanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_language)

        val languageClickListener = View.OnClickListener { 
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // finish() // Optional: if you don't want the user to come back to this screen
        }

        findViewById<Button>(R.id.btn_spanish).setOnClickListener(languageClickListener)
        findViewById<Button>(R.id.btn_runasimi).setOnClickListener(languageClickListener)
        findViewById<Button>(R.id.btn_aymara).setOnClickListener(languageClickListener)
        findViewById<Button>(R.id.btn_ashaninka).setOnClickListener(languageClickListener)
        findViewById<Button>(R.id.btn_shipibo).setOnClickListener(languageClickListener)
    }
}
