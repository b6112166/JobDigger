package com.example.jobdiggerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class JobSeekerActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_seeker_page)


        var submitButton = Button(this);

        submitButton.apply {
            text = "Submit"
            textSize = 20f
            id = R.id.submit1
        }


        submitButton.setOnClickListener({
            Toast.makeText(this, "you clicked me.", Toast.LENGTH_SHORT).show();
        })
    }


}