package com.example.routeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.routeapp.R
import com.example.routeapp.activities.DetailActivity
import com.example.routeapp.models.Trail


class TrailDetailFragment : Fragment() {
    private var trailId: Int = 0
    private var trailType: String? = null

    fun setTrailDetails(trailId: Int, type: String) {
        this.trailId = trailId
        this.trailType = type
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            trailId = savedInstanceState.getInt("trailId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trail_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        val view: View? = view
        if (view != null) {
            val description: TextView = view.findViewById(R.id.textDescription)
            val trail = if (trailType == DetailActivity.TYPE_HARD) {
                Trail.hardTrails[trailId]
            } else {
                Trail.easyTrails[trailId]
            }
            description.text = trail.getDescription()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("trailId", trailId.toLong())
    }

    fun setTrailId(trailId: Int) {
        this.trailId = trailId
    }

    override fun onDestroy() {
        this.trailId = 0
        super.onDestroy()
    }
}