package com.example.vms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class VisitorSlip : AppCompatActivity() {
    lateinit var tv : TextView
    lateinit var tv2 : TextView
    lateinit var tv3 : TextView
    lateinit var tv4 : TextView
    lateinit var tv5 : TextView
    lateinit var tv6 : TextView
    lateinit var tv7 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.visitor_slip)
        val bundle = intent.extras
        val myVariable = bundle?.getString("myKey")
        // val textView = findViewById<TextView>(R.id.textView)

//        textView.text = "The value is $myVariable"
        tv=findViewById(R.id.textView1)
        tv2=findViewById(R.id.textView2)
        tv3=findViewById(R.id.textView3)
        tv4=findViewById(R.id.textView4)
        tv5=findViewById(R.id.textView5)
        tv6=findViewById(R.id.textVw6)
        tv7=findViewById(R.id.textVw7)

        val file = myVariable
        val fin = openFileInput(file)
        var c :Int
        var temp = ""
        while(fin.read().also{c = it }!=-1){
            temp += c.toChar().toString()
        }
        val arr = temp.split(",")
        tv.text=arr[0]
        tv2.text =arr[1]
        tv3.text=arr[2]
        tv4.text=arr[3]
        tv5.text=arr[4]
        tv6.text=arr[5]
        tv7.text=arr[6]


    }
}