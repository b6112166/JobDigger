package com.example.jobdiggerapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView


class RecruiterActivity : AppCompatActivity() {
    //lists to load items from the database
    private var jobTitleList = ArrayList<String>()
    private var skillList = ArrayList<String>()

    private var jobSelection = String();
    private var selectedSkills = ArrayList<String>();

    private var yearsOfExperience = String();

    private var layoutArray = ArrayList<LinearLayout>();//stores different linear layouts for displayed layout switching

    private lateinit var currentLayout:LinearLayout;

    private var currentQuestionNumber = 0;//question number corresponds to the indexes within layout array.

    private lateinit var nextButton:Button
    private lateinit var backButton:Button

    private lateinit var submitButton:Button

    private lateinit var skillAddButton:Button


    private lateinit var selectedSkillArrayAdapter:ArrayAdapter<String>


    private lateinit var skillListArrayAdapter:ArrayAdapter<String>
    private lateinit var jobTitleListArrayAdapter:ArrayAdapter<String>
    //

    private lateinit var programmerAutoCompleteTextView:AutoCompleteTextView
    private lateinit var skillSearchAutoCompleteTextView:AutoCompleteTextView
    private lateinit var yearsOfExperienceEditText:EditText
    private lateinit var selectedSkillListView:ListView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recruiter)


        initializeLists()

        setupLayoutRouting()

        setupViews()
        setupInternalButtons()

    }



    private fun setupViews(){
        programmerAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.programmerAutoCompleteTextView)
        programmerAutoCompleteTextView.threshold = 1
        jobTitleListArrayAdapter = ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,jobTitleList)
        programmerAutoCompleteTextView.setAdapter(jobTitleListArrayAdapter)

        skillSearchAutoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.skillSearchAutoCompleteTextView)
        skillSearchAutoCompleteTextView.threshold =1
        skillListArrayAdapter=ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,skillList)
        skillSearchAutoCompleteTextView.setAdapter(skillListArrayAdapter)


        yearsOfExperienceEditText = findViewById<EditText>(R.id.yearsOfExperienceEditText)

        selectedSkillListView = findViewById<ListView>(R.id.selectedSkillListView)
        selectedSkillArrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,selectedSkills)
        selectedSkillListView.adapter = selectedSkillArrayAdapter

    }

    private fun initializeLists(){
        //initialize the job title list and skill list to be used in the search auto complete
        jobTitleList.add("C++ programmer")
        skillList.add("C++")

    }
    private fun setupLayoutRouting(){
        //add layouts in question order
        layoutArray.add(findViewById(R.id.questionOneLayout))
        layoutArray.add(findViewById(R.id.questionTwoLayout))
        layoutArray.add(findViewById(R.id.questionThreeLayout))
        layoutArray.add(findViewById(R.id.recruiterSubmitLayout))
        currentLayout = layoutArray.get(0)
    }

    private fun setupInternalButtons(){
        nextButton = findViewById<Button>(R.id.nextButton)
        backButton = findViewById<Button>(R.id.backButton)

        submitButton = findViewById<Button>(R.id.recruiterSubmitButton)

        nextButton.setOnClickListener(){

            currentQuestionNumber++;

            updateCurrentLayout()
            updateProgressBar()
            updateButtonVisibility()
        }

        skillAddButton = findViewById<Button>(R.id.skillSearchAddButton)
        skillAddButton.setOnClickListener(){
            val skill = skillSearchAutoCompleteTextView.text.toString()
            selectedSkills.add(skill)
            selectedSkillArrayAdapter.notifyDataSetChanged()


        }

        submitButton.setOnClickListener(){
            //send data to the next activity
            jobSelection = programmerAutoCompleteTextView.text.toString()
            yearsOfExperience = yearsOfExperienceEditText.text.toString()

            val intent = Intent(this, CandidateActivity::class.java)
            var bundle = Bundle()
            bundle.putString("jobSelection",jobSelection)

            bundle.putSerializable("SkillList",(Serializable)selectedSkills)

            bundle.putStringArrayList("SkillList", skillSelection)
            intent.putExtras(bundle)
            startActivity(intent);
        }

        backButton.setOnClickListener(){
            currentQuestionNumber--;

            updateCurrentLayout()
            updateProgressBar()
            updateButtonVisibility()
        }
    }

    private fun updateProgressBar(){
        var progressBar = findViewById<ProgressBar>(R.id.questionnaireProgressBar)
        val progress = (currentQuestionNumber.toFloat()/3.0f*100.0f).toInt()
        progressBar.setProgress(progress,true)

    }

    private fun updateCurrentLayout(){
        //set previous layout to invisible
        currentLayout.visibility = View.INVISIBLE
        //set the new layout to visible
        currentLayout = layoutArray.get(currentQuestionNumber)
        currentLayout.visibility = View.VISIBLE
    }

    private fun updateButtonVisibility(){
        //hide back button at first page
        if(currentQuestionNumber == 0){
            backButton.visibility = View.INVISIBLE

        }
        //hide next button at last page
        else if(currentQuestionNumber ==3){
            nextButton.visibility = View.INVISIBLE
        }
        //enable both for the rest

        else{
            backButton.visibility = View.VISIBLE
            nextButton.visibility = View.VISIBLE
        }

    }
}






