package com.example.luxj.hellokotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import org.jetbrains.anko.toast
import org.jetbrains.anko.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context =this;
        verticalLayout  {
            backgroundColor= ContextCompat.getColor(context,android.R.color.white)
            textView {
                text="TextView"
                textColor= ContextCompat.getColor(context,R.color.colorAccent)
                textSize=30f
                backgroundColor= ContextCompat.getColor(context,android.R.color.holo_blue_bright)
            }
            val name = editText{
                hint="kotlin test"
                hintTextColor= ContextCompat.getColor(context,android.R.color.white)
                textColor= ContextCompat.getColor(context,R.color.colorPrimary)
                textSize=20f
            }
            button("Button") {
                onClick { toast("${name.text}!") }
            }
        }
    }

}
