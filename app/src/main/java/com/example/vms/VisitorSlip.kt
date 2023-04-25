package com.example.vms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
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

        tv=findViewById(R.id.textView1)
        tv2=findViewById(R.id.textView2)
        tv3=findViewById(R.id.textView3)
        tv4=findViewById(R.id.textView4)
        tv5=findViewById(R.id.textView5)
        tv6=findViewById(R.id.textVw6)
        tv7=findViewById(R.id.textVw7)
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

       // val context = applicationContext

        val deleteButton = findViewById<ImageButton>(R.id.deleteButton)
        val del = File(applicationContext.filesDir, myVariable)
        deleteButton.setOnClickListener {
            if (del.exists()) {
              //  val deleted = del.delete()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()


    }}
     u=findViewById(R.id.hello)
        u.setOnClickListener {
            val i=Intent(this, SlipUpdate::class.java)
            i.putExtra("value",myVariable)
            startActivity(i)

        }
    }
}