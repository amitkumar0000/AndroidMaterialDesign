package com.android.materialdesign.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import android.R.attr.x
import android.animation.ObjectAnimator






/*
Animator is base class of
 ValueAnimator
 ObjectAnimator
 AnimatorSet
 */
class PropertyAnimationDemo{

    lateinit var mAnimator:ValueAnimator
    lateinit var mObjAnimator:ObjectAnimator
    fun animateScale(view: View) {

        mAnimator = ValueAnimator.ofInt(0, 500)
        mAnimator.setRepeatCount(4)
        mAnimator.interpolator = LinearInterpolator()
        mAnimator.setDuration(1000)
        mAnimator.addUpdateListener(object: ValueAnimator.AnimatorUpdateListener {
            override  fun onAnimationUpdate(animation: ValueAnimator?) {
                view.getLayoutParams().height = animation?.animatedValue as Int
                view.getLayoutParams().width = animation?.animatedValue as Int
                view.requestLayout()
            }
        })
        mAnimator.start()
    }

    fun animateTransform(view: View) {

        mObjAnimator = ObjectAnimator.ofInt(view,"left" , 100, view.width * 2 - 35).setDuration(2000)
        mObjAnimator.interpolator = LinearInterpolator()
        mObjAnimator.start()
    }



}