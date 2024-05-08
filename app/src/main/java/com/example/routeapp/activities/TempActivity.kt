package com.example.routeapp.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.routeapp.R
import com.example.routeapp.fragments.StoperFragment


class TempActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temp)
        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }
}