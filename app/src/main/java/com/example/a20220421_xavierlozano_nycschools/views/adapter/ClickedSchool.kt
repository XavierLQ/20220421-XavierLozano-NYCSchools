package com.example.a20220421_xavierlozano_nycschools.views.adapter

import com.example.a20220421_xavierlozano_nycschools.model.SchoolListItem

interface ClickedSchool {
    fun OnClickedSchool(schoolItem: SchoolListItem)
}