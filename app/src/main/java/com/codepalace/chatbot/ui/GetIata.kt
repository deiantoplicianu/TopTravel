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
        //setContentView(R.layout.start2)

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




                // create a object for getting phone numbers data from JSONObject
//                val phone = user.getJSONObject("phone")
//                // fetch mobile number
//                val mobile = phone.getString("mobile")
//                // fetch office number
//                val office = phone.getString("office")

                //Now add all the variables to the data model class and the data model class to the array list.
                val userDetails =
                    UserModelClass(id, name, city, country, iata, icao, lattitude)

                //add the details in the list
                usersList.add(userDetails)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        // Set the LayoutManager that this RecyclerView will use.
        rvUsersList.layoutManager = LinearLayoutManager(this)
        // Adapter class is initialized and list is passed in the param.
        val itemAdapter = UserAdapter(this, usersList)
        // adapter instance is set to the recyclerview to inflate the items.
        rvUsersList.adapter = itemAdapter

    }


    /**
     * Method to load the JSON from the Assets file and return the object
     */
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