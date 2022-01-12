package com.codepalace.chatbot.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepalace.chatbot.R
import kotlinx.android.synthetic.main.start2.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class GetIata : AppCompatActivity() {

    var iataaeroport: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val usersList: ArrayList<UserModelClass> = ArrayList()

        try {

            val obj = JSONObject(getJSONFromAssets()!!)
            val usersArray = obj.getJSONArray("users")

            for (i in 0 until usersArray.length()) {

                val user = usersArray.getJSONObject(i)
                val id = user.getString("id")
                val name = user.getString("name")
                val city = user.getString("city")
                val country = user.getString("country")
                val iata = user.getString("iata")
                val icao = user.getString("icao")
                val lattitude = user.getString("latitude")

                if(user.getString("city") == "Barcelona")
                    iataaeroport = user.getString("iata")

                val userDetails =
                    UserModelClass(id, name, city, country, iata, icao, lattitude)
                usersList.add(userDetails)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        rvUsersList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = UserAdapter(this, usersList)
        rvUsersList.adapter = itemAdapter

    }

    private fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myUsersJSONFile = assets.open("airports.json")
            val size = myUsersJSONFile.available()
            val buffer = ByteArray(size)
            myUsersJSONFile.read(buffer)
            myUsersJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}