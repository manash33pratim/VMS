package com.example.vms.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button

import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.vms.MainActivity
import com.example.vms.R
import com.example.vms.VisitorSlip


class ListFragment : Fragment() {


    lateinit var lv: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Visitor List"

        val v=inflater.inflate(R.layout.fragment_list, container, false)
//        b2=v.findViewById(R.id.b2)
//    b2.setOnClickListener{

        val context = context as MainActivity

        val savedFiles = context.fileList()
        savedFiles.reverse()
        val lv = v.findViewById(R.id.list) as ListView
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, savedFiles)
        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
            // Handle click event here
            val intValue = id.toInt()
            val item = lv.getItemAtPosition(intValue).toString()
            //Toast.makeText(requireContext(), "Item clicked:$item ", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), VisitorSlip::class.java)

//Add any necessary extras to the intent
            val bundle = Bundle()
            bundle.putString("myKey", item)
            intent.putExtras(bundle)
//
            // Start the new activity
            startActivity(intent)
        }


        return v

    }


}


