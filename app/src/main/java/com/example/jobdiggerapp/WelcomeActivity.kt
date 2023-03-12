package com.example.jobdiggerapp


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.appcompat.app.AppCompatDelegate

class WelcomeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)//disables nightmode

        setupBeginningAnimation()
        setupRoleSelectionButtons()
    }

    private fun setupBeginningAnimation(){
        val logoView = findViewById<ImageView>(R.id.logoImg)
        val roleSelectionLinearLayout = findViewById<LinearLayout>(R.id.roleSelectionLinearLayout)
        roleSelectionLinearLayout.visibility = View.INVISIBLE

        ObjectAnimator.ofFloat(logoView,"alpha",1f).apply {
            duration = 1000
            start()
            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    roleSelectionLinearLayout.visibility = View.VISIBLE;
                    logoView.animate().translationY(-700f).duration = 500
                    roleSelectionLinearLayout.animate().alpha(1f).duration = 1500

                    }
            })
        }
    }

    private fun setupRoleSelectionButtons() {
        val recruiterBtn = findViewById<Button>(R.id.recruiterButton)
        recruiterBtn.setOnClickListener(){
            val intent = Intent(this,RecruiterActivity::class.java)
            startActivity(intent);
        }

        val jobFinderBtn = findViewById<Button>(R.id.jobSeekerButton)
        jobFinderBtn.setOnClickListener(){
            val intent = Intent(this, JobSeekerActivity::class.java)
            startActivity(intent);
        }
    }
}



