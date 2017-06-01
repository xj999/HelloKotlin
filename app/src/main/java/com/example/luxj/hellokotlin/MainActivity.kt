package com.example.luxj.hellokotlin


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.luxj.hellokotlin.image.MyImageLoader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyImageLoader.instance.loadImage("http://ww4.sinaimg.cn/mw690/77db19c7gw1edc96dgfirj20ay0jgwh4.jpg", img1)
        MyImageLoader.instance.loadBorderCircleImage("http://ww4.sinaimg.cn/mw690/77db19c7gw1edc96dgfirj20ay0jgwh4.jpg", img2, R.color.colorAccent)
        MyImageLoader.instance.loadBorderRoundImage("http://ww4.sinaimg.cn/mw690/77db19c7gw1edc96dgfirj20ay0jgwh4.jpg", img3, R.color.colorAccent)
        MyImageLoader.instance.loadBorderRoundImage("http://img.nga.cn/attachments/mon_201705/31/biQ13m-32tyKrT1kSdw-go.jpg", img4, R.color.colorAccent)
    }

}
