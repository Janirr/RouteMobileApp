package com.example.routeapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.routeapp.R
import com.example.routeapp.activities.DetailActivity
import com.example.routeapp.models.Trail
import com.google.android.material.appbar.CollapsingToolbarLayout

class TrailDetailFragment : Fragment() {
    private var trailId: Int = 0
    private var trailType: String? = null

    fun setTrailDetails(trailId: Int, type: String) {
        this.trailId = trailId
        this.trailType = type
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Manage the stoper fragment here if needed
        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            trailId = savedInstanceState.getInt("trailId")
            trailType = savedInstanceState.getString("trailType")
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
        view?.let { view ->
            val trailImageView = view.findViewById<ImageView>(R.id.trail_image)
            val description: TextView = view.findViewById(R.id.textDescription)
            val collapsingToolbar =
                view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
            val trail = if (trailType == DetailActivity.TYPE_HARD) {
                Trail.hardTrails[trailId]
            } else {
                Trail.easyTrails[trailId]
            }

            collapsingToolbar.title = trail.getName()
            context?.let { context ->
                trailImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        trail.getResourceId()
                    )
                )
            }

            description.text = trail.getDescription()
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("trailId", trailId)
        savedInstanceState.putString("trailType", trailType)
    }

    override fun onDestroy() {
        this.trailId = 0
        super.onDestroy()
    }
}
