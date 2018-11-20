package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewGroup
import com.android.materialdesign.R

class MainActivity : AppCompatActivity() {


    lateinit var scenaA:Scene
    lateinit var scenaB:Scene
    var changed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sceneRoot = findViewById(R.id.sceneRoot) as ViewGroup

        scenaA = Scene.getSceneForLayout(sceneRoot, R.layout.a_scene,this)
        scenaB = Scene.getSceneForLayout(sceneRoot, R.layout.b_scene,this)


    }


    fun onClick(view : View){
        when(view.id){
            R.id.button2 -> {
                var mSlideTransition: Transition = Slide()
                var mFadeTransition: Transition = Fade()
                var mExplodeTransition: Transition =Explode()
                var mAutoTransition: Transition = AutoTransition()
                mFadeTransition.duration = 1000
                mExplodeTransition.duration = 100
                mSlideTransition.duration = 1000
                mAutoTransition.duration = 1000
                if(!changed) {
                    TransitionManager.go(scenaB, mAutoTransition)
                    changed = !changed;
                }else{
                    TransitionManager.go(scenaA,mAutoTransition)
                    changed = !changed
                }
            }
        }
    }
}
