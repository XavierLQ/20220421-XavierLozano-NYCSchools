package com.example.a20220421_xavierlozano_nycschools.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.example.a20220421_xavierlozano_nycschools.databinding.FragmentMainSchoolListBinding
import com.example.a20220421_xavierlozano_nycschools.model.SchoolListItem
import com.example.a20220421_xavierlozano_nycschools.viewmodel.SchoolViewModel
import com.example.a20220421_xavierlozano_nycschools.viewmodel.StatesSchool
import com.example.a20220421_xavierlozano_nycschools.views.adapter.ClickedSchool
import com.example.a20220421_xavierlozano_nycschools.views.adapter.SchoolAdapter

class MainSchoolListFragment : Fragment(), ClickedSchool {

    /**
     * Using [sharedViewModel] in order to share the data between both fragments
     * and also unisng the observe to obtain the data from the [flows] and into
     * the recyclerview's adapter.
     *
     * ALso, implements the [ClickedSchool] interface in order to communicate the
     * data from the clicked school, and then uses the [dbn] to call the function i
     *to run [getScoreDetails], which launches the coroutine.
     */

    private lateinit var binding: FragmentMainSchoolListBinding
    val Adapter by lazy { SchoolAdapter(this) }

    val viewModel: SchoolViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainSchoolListBinding.inflate(layoutInflater)

        binding.schoolRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = Adapter
        }

        viewModel.getSchools()
        viewModel.schoolList.observe(viewLifecycleOwner){
            state -> when(state){
            is StatesSchool.LOADING ->{
                Log.d("Loading Data...", state.toString())}

            is StatesSchool.SchoolsSuccess ->{
                val receivedSchools: List<SchoolListItem> = state.results
                if (receivedSchools != null){
                    Adapter.updateSchoolList(receivedSchools)
                }
            }

            is StatesSchool.ERROR ->{
                throw Exception("Error Loading the Data")
            }
        }
    }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun OnClickedSchool(schoolItem: SchoolListItem) {
        viewModel.getScoreDetails(schoolItem.dbn.toString())
    }
}