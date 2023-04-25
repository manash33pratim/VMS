package com.example.vms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.io.File


class VisitorSlip : AppCompatActivity() {
    lateinit var tv : TextView
    lateinit var tv2 : TextView
    lateinit var tv3 : TextView
    lateinit var tv4 : TextView
    lateinit var tv5 : TextView
    lateinit var tv6 : TextView
    lateinit var tv7 : TextView
    lateinit var tvname: TextView

    lateinit var u: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visitor_slip)
        val bundle = intent.extras
        val myVariable = bundle?.getString("myKey")

        tv=findViewById(R.id.address)
        tv2=findViewById(R.id.phone)
        tv3=findViewById(R.id.reason)
        tv4=findViewById(R.id.starttime)
        tv5=findViewById(R.id.startdate)
        tv6=findViewById(R.id.endtime)
        tv7=findViewById(R.id.enddate)
        tvname=findViewById(R.id.textViewname)





        val file = myVariable
        val fin = openFileInput(file)

        var c :Int
        var temp = ""
        while(fin.read().also{c = it }!=-1){
            temp += c.toChar().toString()
        }
        val arr = temp.split(",")
        tvname.text=file.toString()
        tv.text=arr[0]
        tv2.text =arr[1]
        tv3.text=arr[2]
        tv4.text=arr[3]
        tv5.text=arr[4]
        tv6.text=arr[5]
        tv7.text=arr[6]



        val deleteButton = findViewById<ImageButton>(R.id.deleteButton)
        val del = File(applicationContext.filesDir, myVariable)
        deleteButton.setOnClickListener {
            if (del.exists()) {
//-----------------------------------------------------------------------------------------------
                val builder= AlertDialog.Builder(this) // builder: local variable

                builder.setTitle("Remove") //for title
                    .setMessage("Do you want to remove $myVariable ?")
                    //.setCancelable(true)
                    .setIcon(R.drawable.ic_delete)
                builder.setPositiveButton("Yes"){
                        dialogInterface, which->
                    del.delete()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Removed $myVariable successfully", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("No"){dialog, which->

                }

                val ad: AlertDialog =builder.create()
                ad.show()
            }
//------------------------------------------------------------------------------------------------------------


    }
     u=findViewById(R.id.hello)
        u.setOnClickListener {
            val intent=Intent(this, SlipUpdate::class.java)
            intent.putExtra("value",myVariable)
            startActivity(intent)

        }
    }
}