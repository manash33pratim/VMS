package com.example.vms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        b1= findViewById(R.id.button)
        val inputStream = FileInputStream(file)
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line: String? = reader.readLine()
        b1.setOnClickListener {
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

            }


        }
    }
}