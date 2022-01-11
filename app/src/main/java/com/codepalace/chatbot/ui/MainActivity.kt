package com.codepalace.chatbot.ui

import android.content.Intent
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepalace.chatbot.R
import com.codepalace.chatbot.ui.GetIata
import kotlinx.android.synthetic.main.start.*
import kotlinx.android.synthetic.main.start2.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)



        btn2.setOnClickListener{
            val intent = Intent(this, MainBot::class.java)
            startActivity(intent)

        }

        mapBtn.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)

        }


    }

}