package com.example.vms


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.vms.databinding.ActivityMainBinding
import com.example.vms.ui.fragments.AddFragment
import com.example.vms.ui.fragments.SearchFragment


class MainActivity : AppCompatActivity() {
         lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(AddFragment())

        binding.bottomnav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.add_nav ->replaceFragment(AddFragment())
            R.id.find_nav ->replaceFragment(SearchFragment())
                R.id.list_nav ->replaceFragment(com.example.vms.ui.fragments.ListFragment())
                else ->{

                }
            }
            true
        }

    }
    fun  replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransition= fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.framelayout,fragment)
        fragmentTransition.commit()
    }




}
