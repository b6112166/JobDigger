package com.example.jobdiggerapp.listOfCandidateActivity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.jobdiggerapp.R


private lateinit var desiredCandidates : ArrayList<String>
private lateinit var desiredContactInfo : ArrayList<String>

class CandidiateListFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidiate_list_dialog_fragment)

        grabExtraData()
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            desiredCandidates
        )
        val listView = findViewById<ListView>(R.id.candidateListView)
        listView.adapter = adapter
        adapter.notifyDataSetChanged();
    }

    private fun grabExtraData() {
        val intent = intent
        desiredCandidates = intent.getStringArrayListExtra("Candidate Names") as ArrayList<String>
        desiredContactInfo = intent.getStringArrayListExtra("Candidate Contacts") as ArrayList<String>
    }
}