package com.example.jobdiggerapp.listOfCandidateActivity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdiggerapp.R


class CandidateAdapter(private val context: Context, private val candidateList: ArrayList<Int>) : RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>(){
    class CandidateViewHolder(itemView : View, private val context : Context) : RecyclerView.ViewHolder(itemView){
        private var view: View = itemView
        private var desiredCandidates = ArrayList<String>()
        private var desiredContactInfo = ArrayList<String>()

        //TODO change the type of candidateItem to change the description of candidate and name
        fun bindCandidate(candidateItem : Int) {
            view.findViewById<TextView>(R.id.CandidateNameTextView).text = "Jeffery"
            view.findViewById<TextView>(R.id.CandidateDescriptionTextView).text = "Jeffery is hot"

            val listBtn = view.findViewById<ImageButton>(R.id.listOfCandidateBtn)
            listBtn.setOnClickListener(){
                val intent = Intent(context, CandidiateListFragment::class.java)
                var bundle = Bundle()
                bundle.putStringArrayList("Candidate Names", desiredCandidates)
                bundle.putStringArrayList("Candidate Contacts", desiredContactInfo)
                intent.putExtras(bundle)
                context.startActivity(intent);
            }
        }

        // TODO change the parameter to include chosen candidate info
        fun setupConfirmationDialog(
            candidateItem: Int
        ){
            val confirmationBtn = view.findViewById<ImageButton>(R.id.matchBtn)

            confirmationBtn.setOnClickListener(){
                val alertDialog: AlertDialog? = context?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton("yes",
                            DialogInterface.OnClickListener { dialog, id ->
                                //TODO save chosen candidate to interview list
                                var name = "Bob"
                                desiredCandidates.add(name)
                                var phoneNumber = "604 999 777"
                                desiredContactInfo.add(phoneNumber)
                                dialog.dismiss()
                            })
                        setNegativeButton("no",
                            DialogInterface.OnClickListener { dialog, id ->
                                dialog.dismiss()
                            })
                        setTitle("Are you sure you want this candidate?")
                    }
                    builder.create()
                }
                alertDialog?.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.candidate_container, parent, false)
        return CandidateViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidateItem = candidateList[position]
        holder.bindCandidate(candidateItem)
        holder.setupConfirmationDialog(candidateItem)
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }
}