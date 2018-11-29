package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.materialdesign.R
import android.widget.TextView.BufferType
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.support.design.widget.CoordinatorLayout.Behavior.setTag
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View


class TextFormatActivity : AppCompatActivity() {

    var textview:TextView ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_format)

        textview = findViewById(R.id.textformat)

        makeTextViewResizable(textview, 3, "...more", true);

    }

    private fun makeTextViewResizable(tv: TextView?, maxLine: Int, expandText: String, viewMore: Boolean) {
        if (tv?.getTag() == null) {
            tv?.setTag(tv.getText())
        }
        val vto = tv?.getViewTreeObserver()
        vto?.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                val text: String
                val lineEndIndex: Int
                val obs = tv.getViewTreeObserver()
                obs.removeGlobalOnLayoutListener(this)
                if (maxLine === 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0)
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length + 1).toString() + " " + expandText
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1)
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length + 1).toString() + " " + expandText
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1)
                    text = tv.getText().subSequence(0, lineEndIndex).toString() + " " + expandText
                }
                tv.setText(text)
                tv.setMovementMethod(LinkMovementMethod.getInstance())
                tv.setText(
                    addClickablePartTextViewResizable(
                        Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                        viewMore
                    ), BufferType.SPANNABLE
                )
            }
        })
    }

    fun addClickablePartTextViewResizable(strSpanned: Spanned,tv:TextView,maxLine: Int,spanableText:String,viewMore:Boolean): SpannableStringBuilder {
        val str = strSpanned.toString()
        val ssb = SpannableStringBuilder(strSpanned)

        if (str.contains(spanableText)) {
            ssb.setSpan(object : ClickableSpan() {

                override fun onClick(widget: View) {
                    tv.layoutParams = tv.layoutParams
                    tv.setText(tv.tag.toString(), BufferType.SPANNABLE)
                    tv.invalidate()
                    if (viewMore) {
                        makeTextViewResizable(tv, -1, "View Less", false)
                    } else {
                        makeTextViewResizable(tv, 3, "...more", true)
                    }

                }
            }, str.indexOf(str), str.indexOf(str) + spanableText.length, 0)

        }
        return ssb
    }
}
