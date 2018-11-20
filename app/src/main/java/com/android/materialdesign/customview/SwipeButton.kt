package com.android.materialdesign.customview

import android.annotation.TargetApi
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class SwipeButton : RelativeLayout {
    private val slidingButton: ImageView? = null
    private val initialX: Float = 0.toFloat()
    private val active: Boolean = false
    private val initialButtonWidth: Int = 0
    private val centerText: TextView? = null

    private val disabledDrawable: Drawable? = null
    private val enabledDrawable: Drawable? = null

    constructor(context: Context) : super(context) {

        init(context, null, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        init(context, attrs, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        init(context, attrs, defStyleAttr, -1)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        //Don't worry about this method right now... I'm going to talk about this latter.
    }
}