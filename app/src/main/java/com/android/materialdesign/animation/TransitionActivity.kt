package com.android.materialdesign.animation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.android.materialdesign.R

class TransitionActivity : AppCompatActivity() {

    lateinit var transitionContainer:ViewGroup
    lateinit var tranitionBtn:Button
    lateinit var text:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        transitionContainer = findViewById(R.id.transitions_container)
        tranitionBtn = findViewById(R.id.transitionBtn) as Button
        text = findViewById(R.id.transitionText)

    }

    fun onClick(view : View){
        when(view.id){
            R.id.transitionBtn -> {
                var slide = Slide(Gravity.RIGHT)
                slide.duration = 1000
                slide.interpolator = FastOutSlowInInterpolator()
                TransitionManager.beginDelayedTransition(transitionContainer,slide);

                if(text.visibility == View.GONE)
                    text.visibility = View.VISIBLE
                else
                    text.visibility = View.GONE

            }
        }
    }
}
