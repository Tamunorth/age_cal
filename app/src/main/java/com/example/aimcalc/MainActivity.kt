package com.example.aimcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private  var tvSelectedDate : TextView? = null
    private  var tvTimeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnDatePicker : Button = findViewById(R.id.buttonDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        tvTimeInMinutes = findViewById(R.id.tvTimeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }


    private fun clickDatePicker() {

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

     val dpd =    DatePickerDialog(this,

         {
                 _,selectedYear, selectedMonth, selectedDay ->

             Toast.makeText(this, "Selected Year is $selectedYear" , Toast.LENGTH_LONG).show()

             val selectedDate =  "$selectedDay/${selectedMonth + 1}/$selectedYear"

             tvSelectedDate?.text = selectedDate

             val  sdf =  SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

             val theDate  = sdf.parse(selectedDate);
             theDate?.let {

                 val selectedTimeInMinutes = theDate.time /60000


                 val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {

                    val currentDateInMinutes = currentDate.time / 60000


                    val differenceInMinutes =  currentDateInMinutes - selectedTimeInMinutes;

                    tvTimeInMinutes?.text = differenceInMinutes.toString()
                }

             }



         },

         year, month, day
     )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }






}