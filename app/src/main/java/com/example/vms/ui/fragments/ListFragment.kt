package com.example.vms.ui.fragments

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


class ListFragment : Fragment() {
    lateinit var b2: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Visitor List"

            val v=inflater.inflate(R.layout.fragment_list, container, false)
        b2=v.findViewById(R.id.b2)
        b2.setOnClickListener {
            val context = context as MainActivity

            val savedFiles = context.fileList()
            savedFiles.reverse()
            val lv = context.findViewById(R.id.list) as ListView
            val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, savedFiles)
            lv.adapter = adapter
        }
            return v

        }


}

