package com.android.materialdesign.customview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.android.materialdesign.R
import org.w3c.dom.Text
import android.text.SpannableStringBuilder


class ExpandableTextView : TextView{


    var trimlength:Int = 200
    var trim:Boolean = true
    var bufferType:BufferType?=null
    val ELLIPSIS:String = ".....more"


    var originalText: CharSequence? = null
    var trimmedText: CharSequence? = null

    constructor(ctxt:Context):super(ctxt){
        init(ctxt,null,-1,-1)

    }
    constructor(ctxt:Context,attrSet:AttributeSet):super(ctxt,attrSet){
        init(ctxt,attrSet,-1,-1)
    }
    constructor(ctxt:Context,attrSet:AttributeSet,defStryleAttribute: Int):super(ctxt,attrSet,defStryleAttribute){
        init(ctxt,attrSet,defStryleAttribute,-1)

    }
    constructor(ctxt:Context,attrSet:AttributeSet,defStryleAttribute: Int,defStyleRes:Int):super(ctxt,attrSet,defStyleRes,defStyleRes)
    {
        init(ctxt,attrSet,defStryleAttribute,defStyleRes)

    }


    private fun init(ctxt: Context, attrSet: AttributeSet?, i: Int, i1: Int) {
        var typeArray = ctxt.obtainStyledAttributes(attrSet, R.styleable.ExpandableTextView)
        trimlength = typeArray.getInt(R.styleable.ExpandableTextView_trimLength,0)
        typeArray.recycle()

        setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                trim = !trim
                setText()
                requestFocusFromTouch()
            }
        })
    }

    fun setText(){
        super.setText(getDisplayableText(),bufferType)
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        originalText = text;
        trimmedText = getTrimmedText(text!!);
        bufferType = type;
        setText();

    }

    fun getDisplayableText():CharSequence?{
        return if (trim)
            trimmedText
        else {
              SpannableStringBuilder(originalText, 0, originalText!!.length).append("..less")
        }
    }

    private fun getTrimmedText(text: CharSequence): CharSequence? {
        return if (originalText != null && originalText!!.length > trimlength) {
            SpannableStringBuilder(originalText, 0, trimlength + 1).append(ELLIPSIS)
        } else {
            originalText
        }
    }


    fun setTrimLength(trimLength: Int) {
        this.trimlength = trimLength
        trimmedText = getTrimmedText(originalText!!)
        setText()
    }

    fun getTrimLength(): Int {
        return trimlength
    }




}