package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import com.android.materialdesign.R
import kotlinx.android.synthetic.main.activity_moving_object_on_touch.*
import kotlin.math.roundToInt

class MovingObjectOnTouchActivity : AppCompatActivity() {

    lateinit var image:ImageView
    lateinit var mainLayout:RelativeLayout
    var xDelta = 0
    var yDelta = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moving_object_on_touch)

        image = findViewById(R.id.lockObject)
        mainLayout = findViewById(R.id.main)

        image.setOnTouchListener(object:  View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var x:Int = event?.rawX!!.roundToInt()
                var y:Int = event?.rawY!!.roundToInt()

                when(event?.action){
                    MotionEvent.ACTION_DOWN -> {
                        var lparam = v?.layoutParams as RelativeLayout.LayoutParams

                        xDelta = x - lparam.leftMargin
                        yDelta = y - lparam.topMargin
                    }
                    MotionEvent.ACTION_UP -> {
                        Toast.makeText(applicationContext,
                            "thanks for new location!", Toast.LENGTH_SHORT)
                            .show();
                    }
                    MotionEvent.ACTION_MOVE -> {
                        var lparam = v?.layoutParams as RelativeLayout.LayoutParams
                        lparam.leftMargin = x - xDelta
                        lparam.topMargin = y - yDelta

                        lparam.rightMargin = 0
                        lparam.bottomMargin = 0

                        v?.layoutParams = lparam
                    }
                }
                main.invalidate()
                return true;
            }
        })

    }
}
