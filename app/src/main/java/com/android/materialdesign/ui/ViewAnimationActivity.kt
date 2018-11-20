package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.materialdesign.R
import com.android.materialdesign.animation.ViewAnimationDemo

class ViewAnimationActivity : AppCompatActivity() {

    lateinit var viewAnimationObj: ViewAnimationDemo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_animation)

        viewAnimationObj = ViewAnimationDemo(this)
    }

    fun onClick(view: View){
        when(view.id) {
            R.id.move -> viewAnimationObj.move(view)
            R.id.fade -> viewAnimationObj.fade(view)
            R.id.zoomIn -> viewAnimationObj.zoomIn(view)
            R.id.zoomout -> viewAnimationObj.zoomOut(view)
            R.id.rotate ->  viewAnimationObj.rotate(view)
            R.id.together -> viewAnimationObj.set(view)
        }
    }
}
