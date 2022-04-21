package com.example.a20220421_xavierlozano_nycschools.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220421_xavierlozano_nycschools.R
import com.example.a20220421_xavierlozano_nycschools.model.SchoolListItem

class SchoolAdapter(private val clickedSchool:ClickedSchool) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    val schoolList:MutableList<SchoolListItem> = mutableListOf()


    fun updateSchoolList(newSchools: List<SchoolListItem>){
        schoolList.addAll(newSchools)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val schoolView = LayoutInflater.from(parent.context).inflate(R.layout.school_item, parent, false)
        return SchoolViewHolder(schoolView)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val schoolSelect = schoolList[position]
        holder.SchoolBinding(schoolSelect, clickedSchool)
    }

    override fun getItemCount(): Int = schoolList.size

    class SchoolViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val schoolName: TextView = itemView.findViewById(R.id.SchoolText)

        fun SchoolBinding(schoolSelect: SchoolListItem, clickedSchool: ClickedSchool) {
            schoolName.text = schoolSelect.schoolName

            itemView.setOnClickListener{ view ->
                view.findNavController()
                    .navigate(R.id.action_mainSchoolListFragment_to_schoolDetailsFragment)
                clickedSchool.OnClickedSchool(schoolSelect)
            }

        }

    }
}