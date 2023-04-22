package com.example.vms.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vms.R
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class AddFragment : Fragment() {
    lateinit var b1 : Button
    lateinit var ed : EditText
    lateinit var ph : EditText
    lateinit var re : EditText
    lateinit var txtDate: TextView
    lateinit var txtTime: TextView
    lateinit var txtDate2: TextView
    lateinit var txtTime2: TextView
    lateinit var checkbox: CheckBox

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mHour: Int = 0
    private var mMinute: Int = 0

    lateinit var data:String


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_add, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Add Visitor"
        b1 = v.findViewById(R.id.btnsave)

        ed = v.findViewById(R.id.AddressAdd)
        ph = v.findViewById(R.id.PhoneAdd)
        re = v.findViewById(R.id.ReasonAdd)
        txtDate = v.findViewById(R.id.displayDate)
        txtTime = v.findViewById(R.id.displayTime)

        txtDate2 = v.findViewById(R.id.displayDate2)
        txtTime2 = v.findViewById(R.id.displayTime2)

        checkbox = v.findViewById(R.id.checkbox)

        val fileName = v.findViewById<EditText>(R.id.NameAdd)

            checkbox.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b -> (
                if (compoundButton.isChecked) txtTime.text=CurrentTime() else txtTime.text=""
            )
                if (compoundButton.isChecked) txtDate.text=CurrentDate() else txtDate.text=""

            })


        txtDate.setOnClickListener {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(v.context,
                { view, year, monthOfYear, dayOfMonth
                    ->
                    txtDate.setText("$dayOfMonth-0${monthOfYear + 1}-$year")
                },

                mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        txtTime.setOnClickListener {
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(
                v.context,
                { view, hourOfDay, minute
                    ->
                    var hourOfDay = hourOfDay
                    val AM_PM =
                        if (hourOfDay < 12) {
                            "AM"
                        } else {
                            "PM"
                        }
                    if (AM_PM == "PM") hourOfDay -= 12
                    if (hourOfDay == 0) hourOfDay += 12
                    if (minute < 10)
                        txtTime.setText("$hourOfDay:0$minute $AM_PM")
                    else
                        txtTime.setText("$hourOfDay:$minute $AM_PM")
                },
                mHour, mMinute, true
            )
            timePickerDialog.show()
        }


        txtDate2.setOnClickListener {
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]
            val datePickerDialog = DatePickerDialog(v.context,
                { view, year, monthOfYear, dayOfMonth
                    ->
                    txtDate2.setText("$dayOfMonth-${monthOfYear + 1}-$year")
                },

                mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        txtTime2.setOnClickListener {
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]
            val timePickerDialog = TimePickerDialog(
                v.context,
                { view, hourOfDay, minute
                    ->
                    var hourOfDay = hourOfDay
                    val AM_PM =
                        if (hourOfDay < 12) {
                            "AM"
                        } else {
                            "PM"
                        }
                    if (AM_PM == "PM") hourOfDay -= 12
                    if (hourOfDay == 0) hourOfDay += 12
                    if (minute < 10)
                        txtTime2.setText("$hourOfDay:0$minute $AM_PM")
                    else
                        txtTime2.setText("$hourOfDay:$minute $AM_PM")
                },
                mHour, mMinute, true
            )
            timePickerDialog.show()
        }




        b1.setOnClickListener ()
        {if (fileName.text.isEmpty() && ed.text.isEmpty() && ph.text.isEmpty() && re.text.isEmpty() && txtTime.text.equals("Time") && txtTime2.text.isEmpty() && (txtDate.text.equals("Date")) && txtDate2.text.isEmpty())
        {
            Toast.makeText(context,"Fill the details properly", Toast.LENGTH_LONG).show()

        }else{val file:String = fileName.text.toString()
            data=ed.text.toString()
            data+=",".toString()
            data+=ph.text.toString()
            data+=",".toString()
            data+=re.text.toString()
            data+=",".toString()
            data+=txtTime.text.toString()
            data+=",".toString()
            data+=txtDate.text.toString()
            data+=",".toString()
            data+=txtTime2.text.toString()
            data+=",".toString()
            data+=txtDate2.text.toString()


            try{
                val fout: FileOutputStream = requireContext().openFileOutput(file, Context.MODE_APPEND)
                fout.write(data.toByteArray())
                fout.close()

                Toast.makeText(context,"Visitor Added", Toast.LENGTH_LONG).show()
            }
            catch (to: IOException){

            }
            fileName.text.clear()
            ed.text.clear()
            ph.text.clear()
            re.text.clear()
            txtDate.text=""
           txtTime.text=""
            txtDate2.text=""
            txtTime2.text=""


        }}

        return v
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun CurrentTime(): String {
        val mainhour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH"))
        val minshow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("mm"))

        var hour = mainhour.toInt()
        val min = minshow.toInt()

        val am_pm =
            if (hour < 12) {
                "AM"
            } else {
                "PM"
            }

//--------------------------------------------------------------------------------------------------------------------
        if (am_pm == "PM") hour -= 12
        if (hour == 0) hour += 12


        if (min < 10) {

            return ("$hour:0$minshow $am_pm")
        }else
        {return ("$hour:$minshow $am_pm")}

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun CurrentDate(): String {



        val dateshow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        return dateshow.toString()
    }


}