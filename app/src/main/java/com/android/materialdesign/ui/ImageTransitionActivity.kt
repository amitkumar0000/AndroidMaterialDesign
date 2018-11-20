package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.android.materialdesign.R

class ImageTransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_transition)
    }

    override fun onBackPressed() {
        supportFinishAfterTransition();
    }
}
