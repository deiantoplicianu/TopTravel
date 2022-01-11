package com.codepalace.chatbot.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.codepalace.chatbot.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class DatePic : AppCompatActivity(), DatePickerDialog.OnDateSetListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.datep)

        pickDate()
    }

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    fun pickDate() {

//        calbtn.setOnClickListener {
//
//            getDateCalendar()
//            DatePickerDialog(this, this, year, month, day).show()
//        }
        getDateCalendar()
        DatePickerDialog(this, this, year, month, day).show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        //et_message.setText("$savedYear-$savedMonth-$savedDay")


    }
}