package com.codepalace.chatbot.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codepalace.chatbot.R
import com.codepalace.chatbot.data.Message
import com.codepalace.chatbot.databinding.ActivityMainBinding
import com.codepalace.chatbot.utils.BotResponse
import com.codepalace.chatbot.utils.Constants.OPEN_CITY
import com.codepalace.chatbot.utils.Constants.RECEIVE_ID
import com.codepalace.chatbot.utils.Constants.SEND_ID
import com.codepalace.chatbot.utils.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

class MainBot : AppCompatActivity(), DatePickerDialog.OnDateSetListener{

    private lateinit var binding: ActivityMainBinding

    var messagesList = mutableListOf<Message>()

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var k = 0
    var ok:Long = 0


    var date1: String = ""
    var date2: String = ""
    var date3 = 0
    var date4 = 0
    var date5 = 0

    private lateinit var adapter: MessagingAdapter

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    fun pickDate() {
        getDateCalendar()
        DatePickerDialog(this, this, year, month, day).show()

    }


    var iataaeroport: String = ""
    var iatalatid:String = ""
    var iatalong:String = ""

    fun getIataCode(aeroport : String) {

        try {

            val obj = JSONObject(getJSONFromAssets()!!)
            val usersArray = obj.getJSONArray("users")

            for (i in 0 until usersArray.length()) {

                val user = usersArray.getJSONObject(i)

                if(user.getString("city") == aeroport) {
                    iataaeroport = user.getString("iata")
                    iatalatid = user.getString("latitude")
                    iatalong = user.getString("longitude")
                    break
                }

            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }


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

    private fun getSpeechInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault()
        )
        if(intent.resolveActivity(packageManager) != null)
        {
            startActivityForResult(intent, 10)
        }
        else
        {
            Toast.makeText(this, "Your device doesn't support Speech Input", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)


        recyclerView()
        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hi! I'm Top, your travel assistant :) Together we can plan a perfect trip. If you want to help you, type \"Hello\" or \"Hi\" to start planning your trip: D")
    }

    private fun clickEvents() {

        btnSpeak.setOnClickListener(View.OnClickListener {
            getSpeechInput()
        })

        btn_send.setOnClickListener {
            sendMessage()
        }

        icCalendar.setOnClickListener {
            pickDate()
        }


        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int, data: Intent?
    )
    {
        super.onActivityResult(
            requestCode,
            resultCode, data
        )
        when (requestCode) {
            10 -> if (resultCode == RESULT_OK &&
                data != null
            ) {
                val result =
                    data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    )

                val timeStamp = Time.timeStamp()

                if (result?.get(0)?.isNotEmpty() == true) {

                    messagesList.add(Message(result[0], SEND_ID, timeStamp))
                    et_message.setText("")

                    adapter.insertMessage(Message(result[0], SEND_ID, timeStamp))
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                    botResponse(result[0])
                }

            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            delay(1000)

            withContext(Dispatchers.Main) {
                val response = BotResponse.basicResponses(message)

                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)

                when (response) {


                    OPEN_CITY -> {

                        var mainDeparture = ""
                        getIataCode(BotResponse.iataDep)
                        mainDeparture = iataaeroport

                        var mainArrival = ""
                        var mainLatid = ""
                        var mainLong = ""
                        getIataCode(BotResponse.iataArr)
                        mainArrival = iataaeroport
                        mainLatid = iatalatid
                        mainLong = iatalong

                        val mainDepDate = date1
                        val mainDepArr = date2
                        val mainPers = BotResponse.persNumber

                        val mainDays = ok
                        val site = Intent(Intent.ACTION_VIEW)

                        site.data =
                            Uri.parse("https://www.esky.ro/zbor+hotel/search?departureCode=$mainDeparture&arrivalCode=c-$mainArrival&arrivalLat=$mainLatid&arrivalLon=$mainLong&checkInDate=$mainDepDate&checkOutDate=$mainDepArr&rooms%5B0%5D%5Badults%5D=$mainPers&isFlexSearch=false&rangeStartDate=$mainDepDate&rangeEndDate=$mainDepArr&stayLength=$mainDays,$mainDays")
                        startActivity(site)
                    }

                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month+1
        savedYear = year

        if(k == 0) {
            et_message.setText("Departure: $savedYear-$savedMonth-$savedDay")
            date1 = "$savedYear-$savedMonth-$savedDay"
            date3 = savedYear
            date4 = savedMonth
            date5 = savedDay
            k++
        }
        else {
            date2 = "$savedYear-$savedMonth-$savedDay"
            val datatest1 = LocalDate.of(date3, date4, date5)
            val datatest2 = LocalDate.of(savedYear, savedMonth, savedDay)
            ok = ChronoUnit.DAYS.between(datatest1, datatest2)
            et_message.setText("Return date: $savedYear-$savedMonth-$savedDay")
        }
    }
}