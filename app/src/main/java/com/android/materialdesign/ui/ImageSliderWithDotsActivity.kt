package com.android.materialdesign.ui

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import android.widget.LinearLayout
import com.android.materialdesign.R
import com.android.materialdesign.widget.ImageAdapter

class ImageSliderWithDotsActivity : AppCompatActivity() {

    lateinit var imagePager:ViewPager
    lateinit var sliderDotspanel:LinearLayout
    lateinit var imageAdapter:ImageAdapter
    var dotsCount:Int  = 0
    lateinit var dot: Array<ImageView?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)


        imagePager = findViewById(R.id.imagePager)
        imageAdapter = ImageAdapter(this)
        imagePager.adapter = imageAdapter

        sliderDotspanel = findViewById(R.id.SliderDots)
        dotsCount = imageAdapter.count

        dot = arrayOfNulls<ImageView>(dotsCount)

        for(i:Int in 0..dotsCount-1){
            dot[i] = ImageView(this)
            dot[i]!!.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.non_active_dot
            ))
            var param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            param.setMargins(8,0,8,0)
            sliderDotspanel.addView(dot[i],param)
        }

        dot[0]?.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.active_dot))

        imagePager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                for(i:Int in 0..dotsCount-1){
                    dot[i]!!.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                        R.drawable.non_active_dot
                    ))

                }
                dot[p0]?.setImageDrawable(ContextCompat.getDrawable(applicationContext,
                    R.drawable.active_dot
                ))
            }

        })

    }

}
