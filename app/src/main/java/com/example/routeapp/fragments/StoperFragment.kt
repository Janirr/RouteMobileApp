package com.example.routeapp.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.routeapp.R

class StoperFragment : Fragment(), View.OnClickListener {
    private var seconds = 0
    private var running = false
    private var wasRunning = false
    private var pausedTimeSum = 0L
    private var startTimeTimeStamp = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = requireActivity().getSharedPreferences("StoperPrefs", Context.MODE_PRIVATE)
        startTimeTimeStamp = prefs.getLong("startTimeTimeStamp", System.currentTimeMillis())
        running = prefs.getBoolean("running", false)
        wasRunning = prefs.getBoolean("wasRunning", false)
        calculateInitialSeconds()
    }

    private fun calculateInitialSeconds() {
        if (!running) {
            val currentTime = System.currentTimeMillis()
            seconds = ((currentTime - startTimeTimeStamp) / 1000).toInt()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStoper(layout)
        val startButton: Button = layout.findViewById(R.id.start_button)
        startButton.setOnClickListener(this)
        val stopButton: Button = layout.findViewById(R.id.stop_button)
        stopButton.setOnClickListener(this)
        val resetButton: Button = layout.findViewById(R.id.reset_button)
        resetButton.setOnClickListener(this)
        return layout
    }

    override fun onPause() {
        super.onPause()
        savePreferences()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        calculateInitialSeconds()
        if (wasRunning) {
            running = true
        }
    }

    private fun savePreferences() {
        val prefs = requireActivity().getSharedPreferences("StoperPrefs", Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putLong("startTimeTimeStamp", startTimeTimeStamp)
            putBoolean("running", running)
            putBoolean("wasRunning", wasRunning)
            apply()
        }
    }

    override fun onStop() {
        super.onStop()
        savePreferences()
    }

    private fun onClickStart() {
        startTimeTimeStamp = System.currentTimeMillis() - seconds * 1000  // Adjust startTime to account for elapsed seconds
        running = true
    }

    private fun onClickStop() {
        val currentTime = System.currentTimeMillis()
        pausedTimeSum = currentTime - startTimeTimeStamp
        startTimeTimeStamp += pausedTimeSum
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
        startTimeTimeStamp = System.currentTimeMillis()
        savePreferences() // Resetting the preferences to the initial state
    }

    fun runStoper(view: View) {
        val timeView = view.findViewById<TextView>(R.id.time_view)
        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    seconds = ((System.currentTimeMillis() - startTimeTimeStamp) / 1000).toInt()
                }
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.start_button -> onClickStart()
                R.id.stop_button -> onClickStop()
                R.id.reset_button -> onClickReset()
            }
        }
    }
}
