package com.android.materialdesign.animation

import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.android.materialdesign.R

class DrawableAnimationActivity : AppCompatActivity() {

    lateinit var lockAnimation:AnimationDrawable
    lateinit var lockImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable_animation)

        lockImage = findViewById(R.id.lockView)
        lockImage.setBackgroundResource(R.drawable.lockframe)

        lockAnimation = lockImage.background as AnimationDrawable

    }

    fun onClick(view : View){
        when(view.id){
            R.id.lockView -> lockAnimation.start()
        }
    }
}
