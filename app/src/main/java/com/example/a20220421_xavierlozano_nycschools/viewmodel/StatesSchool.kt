package com.example.a20220421_xavierlozano_nycschools.viewmodel

import com.example.a20220421_xavierlozano_nycschools.model.SchoolListItem
import com.example.a20220421_xavierlozano_nycschools.model.ScoreDetailsItem

sealed class StatesSchool {
    object LOADING: StatesSchool()
    data class SchoolsSuccess(val results: List<SchoolListItem>): StatesSchool()
    data class ScoresSuccess(val results: List<ScoreDetailsItem>): StatesSchool()
    data class ERROR(val error: Throwable): StatesSchool()
}
