package com.android.materialdesign.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.android.materialdesign.R

class PageIndicatorView: View{

    val TAG:String = "PageIndicator"
    lateinit var paint:Paint

    var pivCount =0
    constructor(ctxt: Context):super(ctxt){

    }

    constructor(ctxt: Context,attrSet:AttributeSet):super(ctxt,attrSet){

        var typeArray = context.obtainStyledAttributes(attrSet, R.styleable.PageIndicatorView)
        pivCount = typeArray.getInt(R.styleable.PageIndicatorView_piv_count,0);
        Log.d(TAG," pivCount:: "+ pivCount)
        typeArray.recycle()
        paint = Paint()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)

        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)

        var width = 0

        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize
        }else if(widthMode == MeasureSpec.AT_MOST){
            width = Math.min(10,widthSize)
        }else{
            width = 10
        }

        setMeasuredDimension(width,heightSize)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var x = width/2.0f
        var y = height/2.0f

        var r = height*1.0f
        paint.color = Color.parseColor("#D81B60")
        canvas?.drawCircle(x,y,r,paint)

    }
}