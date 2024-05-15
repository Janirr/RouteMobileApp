package com.example.routeapp.activities

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.routeapp.R

class ActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val imageView: ImageView = findViewById(R.id.background_image)
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background_action))

        // Load and apply fade-in and zoom-in animations
        val fadeInAnimation = AnimatorInflater.loadAnimator(this, R.animator.fade_in) as ObjectAnimator
        fadeInAnimation.setTarget(imageView)  // Setting target directly

        val zoomInAnimation = AnimatorInflater.loadAnimator(this, R.animator.zoom_in) as AnimatorSet
        for (animator in zoomInAnimation.childAnimations) {  // Setting target for all child animations in the set
            (animator as ObjectAnimator).target = imageView
        }

        // Combine animations
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimation, zoomInAnimation)
        animatorSet.start()
    }
}