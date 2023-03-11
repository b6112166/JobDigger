package com.example.jobdiggerapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val logoView = findViewById<ImageView>(R.id.logoImg)
        val roleSelectionLinearLayout = findViewById<LinearLayout>(R.id.roleSelectionLinearLayout)

        val Job_Button = findViewById(R.id.jobFinderBtn) as Button

        Job_Button.setOnClickListener {
            val intent = Intent(this, Job_seeker_Page::class.java)
            startActivity(intent);
        }

        ObjectAnimator.ofFloat(logoView,"alpha",1f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    logoView.animate().translationY(-700f).setDuration(500);
                    roleSelectionLinearLayout.animate().alpha(1f).setDuration(1500);
                }
            })
        }


        /*
        ObjectAnimator.ofFloat(logoView,"layout_marginTop",0f).apply {
            duration = 1000
            start()
        }
        */


    }
}



