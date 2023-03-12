package com.example.jobdiggerapp.listOfCandidateActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.jobdiggerapp.R

private lateinit var viewPager2 : ViewPager2
private lateinit var handler : Handler
private lateinit var candidateList : ArrayList<Int>
private lateinit var candidateAdapter : CandidateAdapter

class CandidateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

        grabExtraData()
        makeListOfCandidates()
        setupCandidateAdapter()
    }

    private fun grabExtraData() {
        val intent = intent
        val jobSelection = intent.getStringExtra("jobSelection")
        val skillSelection = intent.getStringArrayListExtra("SkillList")
    }

    private fun makeListOfCandidates() {
        //TODO implement this
    }

    private fun setupCandidateAdapter() {
        viewPager2 = findViewById(R.id.CandidateViewPage2)
        handler = Handler(Looper.myLooper()!!)
        candidateList = ArrayList()

        //TODO retrieve list of candidates and add it onto the candidateList
        candidateList.add(4)  // DELETE THIS
        candidateList.add(5)  // DELETE THIS

        // Adpater Settings
        candidateAdapter = CandidateAdapter(this, candidateList)
        viewPager2.adapter = candidateAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
}