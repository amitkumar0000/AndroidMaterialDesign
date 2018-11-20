package com.android.materialdesign.customview

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.method.Touch
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.materialdesign.R
import java.util.jar.Attributes
import com.android.materialdesign.R.id.textView
import android.animation.ObjectAnimator



class CustomSwipeButton : RelativeLayout {
    lateinit var slidingBtn: ImageView
    lateinit var centerText: TextView
    var initialX = 0.0f
    var initBtnWidth = 0
    var active = false

    lateinit var disableDrawable: Drawable
    lateinit var enableDrawable: Drawable


    constructor(ctxt: Context) : super(ctxt) {
        init(ctxt, null, -1, -1)
    }

    constructor(ctxt: Context, attr: AttributeSet) : super(ctxt, attr) {

        init(ctxt, attr, -1, -1)
    }

    constructor(ctxt: Context, attr: AttributeSet, defStyleAttr: Int) : super(ctxt, attr, defStyleAttr) {
        init(ctxt, attr, defStyleAttr, -1)

    }

    constructor(ctxt: Context, attr: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        ctxt,
        attr,
        defStyleAttr,
        defStyleRes
    ) {
        init(ctxt, attr, defStyleAttr, defStyleRes)
    }

    private fun init(ctxt: Context, attr: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        var background1 = RelativeLayout(ctxt)
        var layoutParamView = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutParamView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        background1.background = ContextCompat.getDrawable(ctxt, R.drawable.shape_rounded)

        background1.clipChildren = false

        addView(background1, layoutParamView)

        addTextView(background1)

        addImageView(background1)

        setOnTouchListener(getButtonTouchListener())
    }

    private fun addTextView(background: RelativeLayout) {
        var centText = TextView(context)
        centerText = centText

        var layoutParamsView = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParamsView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)

        centText.text = "Swipe"
        centText.setTextColor(Color.WHITE)
        centText.setPadding(35, 35, 35, 35)



        background.addView(centText, layoutParamsView)
    }

    private fun addImageView(background1: RelativeLayout) {
        var swipeBtn = ImageView(context)
        slidingBtn = swipeBtn

        disableDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_lock_open_black_24dp) as Drawable
        enableDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_lock_outline_black_24dp) as Drawable

        slidingBtn.setPadding(44, 44, 44, 44)

        slidingBtn.setImageDrawable(disableDrawable)


        var layoutParamsView = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)


        layoutParamsView.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE)

        layoutParamsView.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

        swipeBtn.background = ContextCompat.getDrawable(context, R.drawable.shape_button)

        swipeBtn.setImageDrawable(disableDrawable)

        background1.addView(swipeBtn, layoutParamsView)

        setOnTouchListener(getButtonTouchListener())

    }

    fun getButtonTouchListener(): OnTouchListener {
        return View.OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_MOVE -> {
                    moveBtn(event)
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    actionUP()
                    return@OnTouchListener true
                }
            }
            false
        }
    }

    private fun actionUP() {
        if(active){
            collapseButton()
        }else{
            initBtnWidth = slidingBtn.width
            if(slidingBtn.x + slidingBtn.width > width*0.85){
                expandButton()
            }else{
                moveButtonBack()
            }
        }
    }

    private fun moveButtonBack() {
    }

    private fun expandButton() {
        var positionAnimator = ValueAnimator.ofFloat(slidingBtn.x,0.0f)
        positionAnimator.addUpdateListener { animation ->
                var pos:Float = positionAnimator.animatedValue as Float
                slidingBtn.x = pos
        }

        var widthAnimator = ValueAnimator.ofInt(slidingBtn.width, width)

        widthAnimator.addUpdateListener { animation ->
            var param = slidingBtn.layoutParams
            param.width = widthAnimator.animatedValue as Int
            slidingBtn.layoutParams = param
        }

        var animatorSet = AnimatorSet()


        animatorSet.addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)

                active = true
                slidingBtn.setImageDrawable(enableDrawable)
            }
        })

        animatorSet.playTogether(positionAnimator,widthAnimator)
        animatorSet.start()

    }

    private fun collapseButton() {
    }

    private fun moveBtn(event: MotionEvent) {
        if(initialX == 0.0f){
            initialX = slidingBtn.x
        }


        var positionAnimator = ValueAnimator.ofFloat(slidingBtn.x,0.0f)
        positionAnimator.addUpdateListener { animation ->
            var pos:Float = positionAnimator.animatedValue as Float
            if(event.x > initialX+slidingBtn.width/2 && event.x < initialX+slidingBtn.width/2){
                slidingBtn.x = event.x - slidingBtn.width/2
                centerText.alpha = 1 - 1.3f*(slidingBtn.x+slidingBtn.width)/width
            }

            if(event.x + slidingBtn.width /2 > width && slidingBtn.x+slidingBtn.width/2 < width){
                slidingBtn.x = (width - slidingBtn.width).toFloat()
            }

            if(event.x < slidingBtn.width/2 && slidingBtn.x >0){
                slidingBtn.x = 0.0f
            }
        }

        positionAnimator.start()


    }
}