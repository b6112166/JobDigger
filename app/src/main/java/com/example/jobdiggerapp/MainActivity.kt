package com.example.jobdiggerapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logoView = findViewById<ImageView>(R.id.logoImg)
        ObjectAnimator.ofFloat(logoView,"alpha",1f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    ObjectAnimator.ofInt(logoView,"translationY",-300).apply {
                        duration = 1000
                        start()
                    }
                }
            })
        }

    }

}



