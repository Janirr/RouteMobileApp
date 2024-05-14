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


class HardTrailFragment : Fragment() {
    private var listener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recyclerView = inflater.inflate(R.layout.fragment_tab2, container, false) as RecyclerView
        val names = arrayOfNulls<String>(Trail.hardTrails.size)
        for (i in names.indices) {
            names[i] = Trail.hardTrails[i].getName()
        }
        val images = arrayOfNulls<Int>(Trail.hardTrails.size)
        for (i in names.indices) {
            images[i] = Trail.hardTrails[i].getResourceId()
        }
        val adapter = CaptionedImagesAdapter(names, images)
        recyclerView.setAdapter(adapter)
        val layoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager
        adapter.setListener(object : CaptionedImagesAdapter.Listener {
            override fun onClick(position: Int) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_TRAIL_ID, position)
                intent.putExtra(DetailActivity.EXTRA_TRAIL_TYPE, DetailActivity.TYPE_HARD)
                activity?.startActivity(intent)
            }
        })

        return recyclerView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.listener = context as Listener
    }
}