package com.example.vms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.w3c.dom.Text
import java.io.*

class SlipUpdate : AppCompatActivity() {
    lateinit var e1: EditText
    lateinit var e2: EditText
    lateinit var e3: EditText
    lateinit var e4: EditText
    lateinit var e5: EditText
    lateinit var e6: EditText
    lateinit var e7: EditText
    lateinit var b1: Button
    lateinit var tvname: TextView

    lateinit var data: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slip_update)

        val value = intent.getStringExtra("value")
        val abc = value.toString()
        val context = applicationContext
        val file = File(context.filesDir, abc)
        data=""
//        val fin = openFileInput(file)
        e1 = findViewById(R.id.e1)
        e2 = findViewById(R.id.e2)
        e3 = findViewById(R.id.e3)
        e4 = findViewById(R.id.e4)
        e5 = findViewById(R.id.e5)
        e6 = findViewById(R.id.e6)
        e7 = findViewById(R.id.e7)
        tvname=findViewById(R.id.visitorname)

        val fle = abc
        val fin = openFileInput(fle)
        supportActionBar?.title = "VMS"
        tvname.setText(abc)
        var c :Int
        var temp = ""
        while(fin.read().also{c = it }!=-1){
            temp += c.toChar().toString()
        }
        val arr = temp.split(",")
        //tvname.text=fle.toString()
        e1.setText(arr[0])
        e2.setText(arr[1])
        e3.setText(arr[2])
        e4.setText(arr[3])
        e5.setText(arr[4])
        e6.setText(arr[5])
        e7.setText(arr[6])





        b1= findViewById(R.id.button)
        val inputStream = FileInputStream(file)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = reader.readLine()
        b1.setOnClickListener {
          //-----------------------------------
            val builder= AlertDialog.Builder(this) // builder: local variable

            builder.setTitle("Save Information") //for title
                .setMessage("Do you want to save it?")
                //.setCancelable(true)


                .setIcon(R.drawable.save)


            builder.setPositiveButton("Save"){
                    dialogInterface, which->
                if (line != null) {
                    val values = line.split(",")
                    data+=e1.text.toString()
                    data+=",".toString()
                    data+=e2.text.toString()
                    data+=",".toString()
                    data+=e3.text.toString()
                    data+=",".toString()
                    data+=e4.text.toString()
                    data+=",".toString()
                    data+=e5.text.toString()
                    data+=",".toString()
                    data+=e6.text.toString()
                    data+=",".toString()
                    data+=e7.text.toString()
                    // Open an output stream and write the modified string
                    val outputStream = FileOutputStream(file)
                    outputStream.write(data.toByteArray())
                    outputStream.close()

                    inputStream.close()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(context,"Updated successfully", Toast.LENGTH_LONG).show()
                }


            }


            builder.setNegativeButton("Cancel"){dialog, which->

            }


            builder.setNeutralButton("Exit") { dialogInterface, which ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }



            val ad: AlertDialog =builder.create()
            ad.show()

        }




        }
    }
