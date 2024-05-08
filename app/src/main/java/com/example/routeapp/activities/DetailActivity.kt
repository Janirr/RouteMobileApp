package com.example.routeapp.activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.routeapp.R
import com.example.routeapp.fragments.TrailDetailFragment
import com.example.routeapp.models.Trail
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedinstanceState: Bundle?) {
        super.onCreate(savedinstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val trailId = intent.getIntExtra(EXTRA_TRAIL_ID, 0)
        val trailType = intent.getStringExtra(EXTRA_TRAIL_TYPE)

        val trail = if (trailType == TYPE_HARD) {
            Trail.hardTrails[trailId]
        } else {
            Trail.easyTrails[trailId]
        }

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
        collapsingToolbar.title = trail.getName()

//        val textView = findViewById<TextView>(R.id.textTitle)
//        textView.text = trail.getName()
        val trailImage: Int = trail.getResourceId()
        val imageView = findViewById<ImageView>(R.id.trail_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, trailImage))
//        imageView.setContentDescription(trail.getName())

        val fragment: TrailDetailFragment =
            supportFragmentManager.findFragmentById(R.id.detail_frag) as TrailDetailFragment
        if (trailType != null) {
            fragment.setTrailDetails(trailId, trailType)
        }
    }

    companion object {
        const val EXTRA_TRAIL_ID = "id"
        const val EXTRA_TRAIL_TYPE = "com.example.routeapp.TRAIL_TYPE"
        const val TYPE_EASY = "EASY"
        const val TYPE_HARD = "HARD"
    }
}

