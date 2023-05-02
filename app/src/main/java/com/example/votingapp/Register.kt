package com.example.votingapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.votingapp.databinding.ActivityMainBinding
import com.example.votingapp.databinding.ActivityMainPageBinding
import com.example.votingapp.databinding.ActivityRegisterBinding
import kotlinx.coroutines.*
import java.util.*


class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var appdb: VotersDatabase
    lateinit var viewModel: VoterViewModel
    private var number: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rnd = Random()

        appdb = VotersDatabase.getDatabase(this)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[VoterViewModel::class.java]

        binding.btn.setOnClickListener {
            val firstname = binding.first.text.toString()
            val firstName = binding.first.text.toString()
            val lastname = binding.last.text.toString()
            val aadhar = binding.aadharNumber.text.toString()
//        val aadharnum=Integer.parseInt(aadhar)
//
            var radioGroup = binding.radiogroup;
            val selectedId: Int = radioGroup.checkedRadioButtonId
            val genderradioButton = findViewById<View>(selectedId) as RadioButton
//
            val gender = genderradioButton.text.toString()
//

            val state = binding.state.text.toString()
            val dob = binding.dob.text.toString()
            number = rnd.nextInt(999999)
            val voterid = binding.voterid

            voterid.text = "Generated Voter Id: " + number.toString();
            if (firstName.isNotEmpty() && lastname.isNotEmpty() && aadhar.isNotEmpty() && state.isNotEmpty() && dob.isNotEmpty()) {
                GlobalScope.launch {
                    val cl = viewModel.findCount(number)
                    if (cl != 0) {
                        showview()
                    } else {
                        viewModel.insertStudent(
                            Voters(
                                number,
                                firstName,
                                lastname,
                                aadhar.toInt(),
                                gender,
                                dob,
                                state
                            )
                        )
                        giveToast()
                    }
                }
            } else {
                Toast.makeText(this, "Fill All Values", Toast.LENGTH_SHORT).show()
            }


        }


    }
    public fun dateselect(view:View){
        val txtDate=findViewById<EditText>(R.id.dob)
        var mYear:Int =0
        var mMonth:Int =0
      var mDay:Int =0
            val c = Calendar.getInstance()
            mYear=c[Calendar.YEAR]
            mMonth=c[Calendar.MONTH]
            mDay=c[Calendar.DAY_OF_MONTH]
            val datePickerDialog= DatePickerDialog(
                this,
                { view , year, monthOfYear , dayOfMonth ->
                    txtDate.setText(
                        dayOfMonth.toString()+"-" + (monthOfYear+1)+"-" + year
                    )
                }, mYear,mMonth,mDay
            )
            datePickerDialog.show()

    }

    private suspend fun giveToast() {
        withContext(Dispatchers.Main) {
            givetoast()
        }
    }

    private fun givetoast() {
        Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
    }

    suspend private fun showview() {
        withContext(Dispatchers.Main){
            giveInfo()
        }
    }

    private fun giveInfo() {
        Toast.makeText(this, "Alredy Registered with Same Registreation No", Toast.LENGTH_SHORT).show()
    }




}












//    private fun writedata() {
////       val firstname=binding.first.text.toString()
//        val firstName = binding.first.text.toString()
//        val lastname = binding.last.text.toString()
//        val aadhar = binding.aadharNumber.text.toString()
////        val aadharnum=Integer.parseInt(aadhar)
////
//        var radioGroup = binding.radiogroup;
//        val selectedId: Int = radioGroup.checkedRadioButtonId
//        val genderradioButton = findViewById<View>(selectedId) as RadioButton
////
//        val gender = genderradioButton.text.toString()
////
//
//        val state = binding.state.text.toString()
//        val dob = binding.dob.text.toString()
//        val voterid = binding.voterid
//
//        voterid.text = "Generated Voter Id: " + number.toString();
//
////        if (firstName.isNotEmpty() && lastname.isNotEmpty() && aadhar.isNotEmpty() && state.isNotEmpty() && dob.isNotEmpty()) {
////
////           val voter=Voters(number,firstName,lastname, aadhar.toInt(),gender,dob,state)
////
////            GlobalScope.launch (Dispatchers.IO){
////                appdb.getVoterDao().insert(voter)
////            }
////
////            Toast.makeText(this, "Successfully added record", Toast.LENGTH_SHORT).show()
////        }
//
//        if (firstName.isNotEmpty() && lastname.isNotEmpty() && aadhar.isNotEmpty() && state.isNotEmpty() && dob.isNotEmpty()) {
//            GlobalScope.launch(Dispatchers.IO) {
//                    viewModel.insertStudent(Voters(number, firstName, lastname, aadhar.toInt(), gender, dob, state))
//                Toast.makeText(applicationContext, "Added successfully", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        else{
//            Toast.makeText(this, "Please Enter Data", Toast.LENGTH_SHORT).show()
//        }
//    }






//lateinit var viewModel: VoterViewModel
//lateinit var VoterDb: VotersDatabase
//
//@SuppressLint("MissingInflatedId", "WrongViewCast")
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_register)
//
//    VoterDb = VotersDatabase.getDatabase(this)
//
//    val btn = findViewById<Button>(R.id.btn)
//    viewModel = ViewModelProvider(
//        this,
//        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//    ).get(VoterViewModel::class.java)
//
//    btn.setOnClickListener {
//        val firstName = findViewById<EditText>(R.id.first).text.toString()
//        val lastname = findViewById<EditText>(R.id.last).text.toString()
//        val aadhar = findViewById<EditText>(R.id.aadharNumber).text.toString()
//        val aadharnum=Integer.parseInt(aadhar)
//
//        var radioGroup=findViewById<RadioGroup>(R.id.radiogroup);
//        val selectedId: Int = radioGroup.checkedRadioButtonId
//        val genderradioButton = findViewById<View>(selectedId) as RadioButton
//
//        val gender = genderradioButton.text.toString()
//
//
//        val state = findViewById<EditText>(R.id.state).text.toString()
//        val dob = findViewById<EditText>(R.id.dob).text.toString()
//        val voterid = findViewById<TextView>(R.id.voterid).text.toString()
//
//        if (firstName.isNotEmpty() && lastname.isNotEmpty() && aadhar.isNotEmpty() && state.isNotEmpty() && dob.isNotEmpty()) {
//            GlobalScope.launch {
//                val cl = viewModel.findCount(firstName)
//                if (cl != 0) {
//                    showview()
//                }else{
//                    val rnd = Random()
//                    val number: Int = rnd.nextInt(999999)
//                    viewModel.insertStudent(Voters(number,firstName,lastname,aadharnum,gender,dob,state))
//                    giveToast()
//                }
//            }
//        } else {
//            Toast.makeText(this, "Fill All Values", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
//
//private suspend fun giveToast() {
//    withContext(Dispatchers.Main) {
//        givetoast()
//    }
//}
//
//private fun givetoast() {
//    Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
//}
//
//suspend private fun showview() {
//    withContext(Dispatchers.Main){
//        giveInfo()
//    }
//}
//
//private fun giveInfo() {
//    Toast.makeText(this, "Already Registered with Same Registration No", Toast.LENGTH_SHORT).show()
//}