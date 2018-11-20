package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.materialdesign.R
import com.android.materialdesign.animation.PropertyAnimationDemo

class PropertyAnimationActivity : AppCompatActivity() {

    lateinit var propertyAnimation:PropertyAnimationDemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)

        propertyAnimation = PropertyAnimationDemo()
    }

    fun onClick(view: View){

        when(view.id){
            R.id.scaleP -> propertyAnimation.animateScale(view)
            R.id.transform -> propertyAnimation.animateTransform(view)
        }
    }
}
