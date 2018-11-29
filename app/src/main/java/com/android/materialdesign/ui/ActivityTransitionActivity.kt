package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.materialdesign.R
import android.support.v4.app.ActivityOptionsCompat
import android.content.Intent
import android.widget.ImageView
import android.support.v4.util.Pair
import android.view.animation.Animation
import android.widget.TextView

class ActivityTransitionActivity : AppCompatActivity() {

    var imageview:ImageView?=null
    var catText:TextView?=null
    var enterAnimationId:Int?=null
    var exitAnimationId:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_activity)

        imageview = findViewById(R.id.catImage)
        catText = findViewById(R.id.catText)

        enterAnimationId = R.anim.translate
        exitAnimationId = R.anim.fade_in
    }

    fun onClick(view: View){
        when(view.id){
            R.id.catImage -> {
                val intent = Intent(this, ImageTransitionActivity::class.java)

                val p1:Pair<View,String> = Pair.create(imageview as View, "profile")
//                val p2:Pair<View,String> = Pair.create(catText as View, "profile")

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,p1)
                startActivity(intent, options.toBundle())

           /*     val intent = Intent(this, ImageTransitionActivity::class.java)
                startActivity(intent)
                overridePendingTransition(enterAnimationId!!, exitAnimationId!!)*/
            }
        }
    }
}
