package com.example.routeapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routeapp.R
import com.example.routeapp.activities.DetailActivity
import com.example.routeapp.adapters.CaptionedImagesAdapter
import com.example.routeapp.models.Trail
import com.example.routeapp.util.Listener


class EasyTrailFragment : Fragment() {
    private var listener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recyclerView =
            inflater.inflate(R.layout.fragment_tab1, container, false) as RecyclerView

        val names = arrayOfNulls<String>(Trail.easyTrails.size)
        for (i in names.indices) {
            names[i] = Trail.easyTrails[i].getName()
        }
        val images = arrayOfNulls<Int>(Trail.easyTrails.size)
        for (i in names.indices) {
            images[i] = Trail.easyTrails[i].getResourceId()
        }
        val adapter = CaptionedImagesAdapter(names, images)
        recyclerView.setAdapter(adapter)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager

        adapter.setListener(object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val trailType = DetailActivity.TYPE_EASY

                if (activity?.findViewById<View>(R.id.detail_fragment_container) != null) {
                    val trailDetailFragment = TrailDetailFragment()
                    trailDetailFragment.setTrailDetails(position, trailType)
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.detail_fragment_container, trailDetailFragment)
                        ?.commit()
                } else {
                    val intent = Intent(activity, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TRAIL_ID, position)
                    intent.putExtra(DetailActivity.EXTRA_TRAIL_TYPE, trailType)
                    activity?.startActivity(intent)
                }
            }
        })
        return recyclerView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.listener = context as Listener
    }
}