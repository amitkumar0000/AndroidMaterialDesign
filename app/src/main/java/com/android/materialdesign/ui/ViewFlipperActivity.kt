package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.ViewFlipper
import com.android.materialdesign.R
import android.view.animation.AnimationUtils.loadAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView


class ViewFlipperActivity : AppCompatActivity() {

    lateinit var viewFlipper:ViewFlipper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper)

        viewFlipper = findViewById(R.id.view_flipper)

        var imageView = ImageView(this)
        imageView.setImageResource(R.drawable.cat)
        viewFlipper.addView(imageView)
    }

    fun onClick(view: View){
        when(view.id){
            R.id.startFlip -> {
                viewFlipper.setFlipInterval(1000)
                var inA:Animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
                var outA:Animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

                viewFlipper.setInAnimation(inA)
                viewFlipper.setOutAnimation(outA)

                viewFlipper.startFlipping()
            }
            R.id.stopFlip -> viewFlipper.stopFlipping()
        }
    }
}
