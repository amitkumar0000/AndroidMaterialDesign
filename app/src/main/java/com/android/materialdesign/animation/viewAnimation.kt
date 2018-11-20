package com.android.materialdesign.animation

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.android.materialdesign.R

/*
Translate, Scale, fade_in, rotate
 */
class ViewAnimationDemo(var context: Context){
    lateinit var manimation:Animation

    fun move(view: View){
        startAnimation(view,R.anim.translate)
    }

    fun zoomIn(view: View){
        startAnimation(view,R.anim.zoomin)
    }

    fun zoomOut(view: View){
        startAnimation(view,R.anim.zoomout)
    }

    fun fade(view: View){
        startAnimation(view,R.anim.fade_in)
    }

    fun rotate(view: View){
        startAnimation(view,R.anim.rotate)
    }

    fun set(view: View){
        startAnimation(view,R.anim.together)
    }

    private fun startAnimation(view:View,animId:Int){
        manimation = AnimationUtils.loadAnimation(context,animId)
        view.startAnimation(manimation)
    }
}