package com.codepalace.chatbot.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codepalace.chatbot.R
import kotlinx.android.synthetic.main.start.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)



        btn2.setOnClickListener{
            val intent = Intent(this, MainBot::class.java)
            startActivity(intent)

        }
    }

}