package com.example.a20220421_xavierlozano_nycschools.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a20220421_xavierlozano_nycschools.databinding.FragmentSchoolDetailsBinding
import com.example.a20220421_xavierlozano_nycschools.model.ScoreDetailsItem
import com.example.a20220421_xavierlozano_nycschools.viewmodel.SchoolViewModel
import com.example.a20220421_xavierlozano_nycschools.viewmodel.StatesSchool
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SchoolDetailsFragment : Fragment()  {

    /**
     * also uses the [sharedViewModel], to obtain the data of the selected school
     * and the method is received with an observe, in order to be displayed in
     * the Textviews.
     */

    val viewModel: SchoolViewModel by sharedViewModel()
    private lateinit var binding: FragmentSchoolDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewModel.schoolList.observe(viewLifecycleOwner) {
                state -> when(state){
            is StatesSchool.LOADING ->{
                Log.d("Loading Score Data...", state.toString())}

            is StatesSchool.ScoresSuccess ->{
                val receivedSchools: ScoreDetailsItem = state.results[0]
                if (receivedSchools != null){

                    binding.SchoolName.text = "School Name: ${receivedSchools.schoolName}"
                    binding.TestTakers.text = "SAT Test Takers: ${receivedSchools.numOfSatTestTakers}"
                    binding.CriticalReadingScore.text = "Critical Reading Avg Score: ${receivedSchools.satCriticalReadingAvgScore}"
                    binding.MathScore.text = "Math Avg Score: ${receivedSchools.satMathAvgScore}"
                    binding.WritingScore.text = "Writing Avg Score: ${receivedSchools.satWritingAvgScore}"

                }
            }

            is StatesSchool.ERROR ->{
                throw Exception("Error Loading the Data")
            }
        }
        }

        binding = FragmentSchoolDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SchoolDetailsFragment()
                }

}
