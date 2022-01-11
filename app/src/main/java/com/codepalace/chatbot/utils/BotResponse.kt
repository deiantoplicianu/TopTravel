package com.codepalace.chatbot.utils

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codepalace.chatbot.R
import android.content.Intent
import android.os.Build.VERSION_CODES.O
import android.widget.DatePicker
import androidx.core.content.ContextCompat.startActivity
import com.codepalace.chatbot.ui.DatePic
import com.codepalace.chatbot.ui.GetIata
import com.codepalace.chatbot.ui.MainBot
import com.codepalace.chatbot.ui.UserModelClass
import com.codepalace.chatbot.utils.Constants.OPEN_CALENDAR
import com.codepalace.chatbot.utils.Constants.OPEN_CITY
import com.codepalace.chatbot.utils.Constants.OPEN_GOOGLE
import com.codepalace.chatbot.utils.Constants.OPEN_SEARCH
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.start.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

object BotResponse : AppCompatActivity(){

    var iataDep: String = ""
    var iataArr: String = ""
    var dateDeparture: String = ""
    var dateArrival: String = ""
    var daysNumber: String = ""
    var persNumber: String = ""
    var arrLatitude: String = ""
    var arrLong: String = ""


    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message =_message.toLowerCase()


        return when {

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Amazing! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    1 -> "Excellent! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    2 -> "Great! First, let's find the perfect flight. Type the following structure: \"your origin city to your departure city\" (example: Munich to Venice)"
                    else -> "error" }
            }

            message.contains("to") ->{
                var plecare: String?= message.substringBeforeLast("to")
                var sosire: String?= message.substringAfterLast("to")

                if (plecare != null) {
                    plecare = plecare.dropLast(1)
                    iataDep = plecare.capitalize()
                }
                if (sosire != null) {
                    sosire = sosire.drop(1)
                    iataArr = sosire.capitalize()
                }

                if(iataArr == "Amsterdam")
                    "Wow! Amsterdam is a unique city! Enjoy the historic buildings, beautiful museums and pleasant ambiance. Go for a boat tour on the canals, stroll through Vondelpark, and go shopping in the inner city. When do you want to leave? Use the calendar icon from the bar to select date"

                else
                    if(iataArr == "Barcelona")
                        "Nice! Barcelona sited between the sea and the mountains, has found a formidable balance: a foot in the traditional things and the other in the avant-garde. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Copenhagen")
                        "Amazing! Copenhagen is known for being the city of fairy tales. Reputed to be happiest city in the world, Copenhagen is known for its canals, excellent food, Tivoli Gardens and for once being home to the children's writer, Hans C Andersen. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    if(iataArr == "Dublin")
                        "Wow! Framed by mountains, centred on a river and edged by a beautiful bay, Dublin's streets and alleys are filled with vibrant art and historic buildings, hip cafés and traditional \"old man\" pubs, as Dubliners call them. When do you want to leave? Use the calendar icon from the bar to select date"
                else
                    "I set $iataDep as your origin and $iataArr as your destination. What's your departure date? Use the calendar icon from the bar to select date"

            }

            message.contains("departure") -> {

                "When do you want to return?"
            }

            message.contains("return") -> {

                "How many people are you? Please type x adults"
            }

//            message.contains("days") -> {
//                val daysDif: String?= message.substringBeforeLast(" days")
//
//                if (daysDif != null) {
//                    daysNumber = daysDif
//                }
//
//
//
//                "Nice. So you will stay $daysNumber days in $iataArr. How many people are you? Please type x adults"
//            }

            message.contains("adults") -> {
                val noP: String?= message.substringBeforeLast(" adults")

                if (noP != null) {
                    persNumber = noP
                }

                "You will be redirected to our partner. Type ok if your agree"
            }



            message.contains("ok") -> {
                    //"Ok. I set Barcelona as your destination. What's your departure city?"
                OPEN_CITY
            }



            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time") && message.contains("?")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }


}