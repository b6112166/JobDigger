package com.example.jobdiggerapp.listOfCandidateActivity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdiggerapp.R


class CandidateAdapter(private val context: Context, private val candidateList: ArrayList<Int>) : RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>(){
    class CandidateViewHolder(itemView : View, private val context : Context) : RecyclerView.ViewHolder(itemView){
        private var view: View = itemView

        //TODO change the type of candidateItem to change the description of candidate and name
        fun bindCandidate() {
            view.findViewById<TextView>(R.id.CandidateNameTextView).text = "Jeffery"
            view.findViewById<TextView>(R.id.CandidateDescriptionTextView).text = "Jeffery is hot"
        }

        // TODO change the parameter to include chosen candidate info
        fun setupConfirmationDialog(position: Int) {
            val confirmationBtn = view.findViewById<ImageButton>(R.id.matchBtn)

            confirmationBtn.setOnClickListener(){
                val alertDialog: AlertDialog? = context?.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton("yes",
                            DialogInterface.OnClickListener { dialog, id ->
                                //TODO save chosen candidate to interview list
                            })
                        setNegativeButton("no",
                            DialogInterface.OnClickListener { dialog, id ->
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
        holder.bindCandidate()
        holder.setupConfirmationDialog(position)
    }

    override fun getItemCount(): Int {
        return candidateList.size
    }
}