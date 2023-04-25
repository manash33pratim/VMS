package com.example.vms.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.vms.R


class SearchFragment : Fragment() {
    lateinit var show: Button
    lateinit var tvName : TextView
    lateinit var tvPhone : TextView
    lateinit var tvReason : TextView
    lateinit var tvAddress : TextView
    lateinit var tvtimeStart : TextView

    lateinit var tvdateStart : TextView
    lateinit var tvtimeEnd : TextView
    lateinit var tvdateEnd : TextView
    lateinit var slip: CardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Find visitor"
        val v= inflater.inflate(R.layout.fragment_search, container, false)
        show=v.findViewById(R.id.btnshow)

        tvName=v.findViewById(R.id.showName)
        tvPhone=v.findViewById(R.id.showPhone)
        tvReason=v.findViewById(R.id.showAddress)
        tvAddress=v.findViewById(R.id.showReason)
        tvtimeStart=v.findViewById(R.id.showtimeStart)
        tvdateStart=v.findViewById(R.id.showdateStart)
        tvtimeEnd=v.findViewById(R.id.showtimeEnd)
        tvdateEnd=v.findViewById(R.id.showdateEnd)
        slip=v.findViewById(R.id.slipLayout)

        slip.visibility=View.INVISIBLE

        val fileName = v.findViewById<EditText>(R.id.searchName)
        show.setOnClickListener()
                {


            val file = fileName.text.toString()

            try {
                val fin = requireContext().openFileInput(file)
                var c :Int
                var temp = ""
                while(fin.read().also{c = it }!=-1){
                    temp += c.toChar().toString()
                }
                val arr = temp.split(",")
                slip.visibility=View.VISIBLE
                tvName.text=file
                tvReason.text =arr[0]
                tvPhone.text=arr[1]
                tvAddress.text=arr[2]
                tvtimeStart.text=arr[3]
                tvdateStart.text=arr[4]
                tvtimeEnd.text=arr[5]
                tvdateEnd.text=arr[6]

                Toast.makeText(context,"Visitor found", Toast.LENGTH_LONG).show()
            }
            catch (e:java.lang.Exception){

                Toast.makeText(context,"Visitor not found", Toast.LENGTH_LONG).show()
            }
        }
        return v
    }


}