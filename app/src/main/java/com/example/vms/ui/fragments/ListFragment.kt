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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.title = "Visitor List"

        val v=inflater.inflate(R.layout.fragment_list, container, false)

        val context = context as MainActivity

        val savedFiles = context.fileList()
        savedFiles.reverse()
        val lv = v.findViewById(R.id.list) as ListView
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, savedFiles)
        lv.adapter = adapter
        lv.setOnItemClickListener { parent, view, position, id ->
      val intValue = id.toInt()
            val item = lv.getItemAtPosition(intValue).toString()
             val intent = Intent(requireContext(), VisitorSlip::class.java)


            val bundle = Bundle()
            bundle.putString("myKey", item)
            intent.putExtras(bundle)

            startActivity(intent)
        }


        return v

    }


}


