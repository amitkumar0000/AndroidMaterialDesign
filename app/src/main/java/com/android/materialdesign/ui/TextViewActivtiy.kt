package com.android.materialdesign.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.materialdesign.R
import com.android.materialdesign.customview.ExpandableTextView
import java.io.*
import java.lang.Exception
import java.net.URL
import java.net.UnknownHostException
import java.nio.charset.Charset


class TextViewActivtiy : AppCompatActivity() {

    var spannableText: TextView? = null

    private val BUFFER_SIZE = 1024 * 10
    private val ZERO = 0
    private val dataBuffer = ByteArray(BUFFER_SIZE)
    private val urlObject: URL? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable_text_view_activtiy)

        spannableText = findViewById(R.id.spannableText)

        val termsOfService = getString(R.string.terms_of_service)
        val privacyPolicy = getString(R.string.privacy_policy)


        val yourText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Ut volutpat interdum interdum. Nulla laoreet lacus diam, vitae " +
                "sodales sapien commodo faucibus. Vestibulum et feugiat enim. Donec " +
                "semper mi et euismod tempor. Sed sodales eleifend mi id varius. Nam " +
                "et ornare enim, sit amet gravida sapien. Quisque gravida et enim vel " +
                "volutpat. Vivamus egestas ut felis a blandit. Vivamus fringilla " +
                "dignissim mollis. Maecenas imperdiet interdum hendrerit. Aliquam" +
                " dictum hendrerit ultrices. Ut vitae vestibulum dolor. Donec auctor ante" +
                " eget libero molestie porta. Nam tempor fringilla ultricies. Nam sem " +
                "lectus, feugiat eget ullamcorper vitae, ornare et sem. Fusce dapibus ipsum" +
                " sed laoreet suscipit. "

        val expandableTextView = findViewById(R.id.expandableText) as ExpandableTextView
        expandableTextView.text = yourText

        Thread(Runnable {
            var s = read()
            Log.d("Hello ", s+ "\n========================JAVAJAVA===============") }
        ).start()

    }


    fun read(): String? {
        val sb = StringBuilder()
        var map = HashMap<String,Int>()
        var body:String
        try {
            val url = URL("https://blog.truecaller.com/2018/01/22/life-as-an-android-engineer/")
            val con = url.openConnection()
            val `in` = con.getInputStream()
            var encoding:Charset = Charset.forName("UTF-8")

            val baos = ByteArrayOutputStream()
            var word = ByteArrayOutputStream()
            var buf = ByteArray(1)
            var len = 0
            var count = 0

            while (len!= -1) {
                len = `in`.read(buf)
                if(len == -1)
                    break

                count++

                if(count == 10){
                    Log.d("10th Character "," ${buf[0].toChar()}   ${buf[0].toInt()} ")
                    count = 0
                }

                if(buf[0].toInt() != 32 && buf[0].toInt()!=13 && buf[0].toInt()!=10 && buf[0].toInt()!=9
                && buf[0].toInt()!=46 && buf[0].toChar() != ',' && buf[0].toChar() != ':' ){
                    word.write(buf,0,len)
                }else{
                    var wordStr = String(word.toByteArray(), encoding)
                    Log.d("Word:: ",wordStr )
                    if(map.containsKey(wordStr)){
                        var c:Int? = map.get(wordStr)
                        c = c?.plus(1)
                        map.put(wordStr,c!!)
                    }else{
                        map.put(wordStr,1)
                    }
                    word.reset()
                }


                baos.write(buf, 0, len)

            }
            body = String(baos.toByteArray(), encoding)


        } catch (e: UnknownHostException) {
            return null

        } catch (e: IOException) {
            return null
        }

        return body
    }
}
