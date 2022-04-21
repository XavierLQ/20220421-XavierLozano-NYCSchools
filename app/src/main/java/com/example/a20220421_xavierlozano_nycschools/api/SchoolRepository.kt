package com.example.a20220421_xavierlozano_nycschools.api

import com.example.a20220421_xavierlozano_nycschools.viewmodel.StatesSchool
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SchoolRepository {
    fun getSchools(): Flow<StatesSchool>
    fun getScores(id: String): Flow<StatesSchool>
}

class SchoolRepositoryImpl(
    private val schoolAPI: SchoolAPI
) : SchoolRepository {

    /**
     * the function [getSchools] call the api to fill the recyclerview with
     * the list of schools and the [getScores] function fills the details fragment
     * once its clicked, using the schools id.
    * */

    override fun getSchools(): Flow<StatesSchool> = flow {
        try {
            val schoolResult = schoolAPI.getSchools()
            if (schoolResult.isSuccessful) {
                schoolResult.body()?.let {
                    emit(StatesSchool.SchoolsSuccess(it))
                } ?: throw Exception("BODY IS NULL")
            } else {
                throw Exception("RESULT GOES TO FAILURE")
            }

        } catch (e: Exception) {
            emit(StatesSchool.ERROR(e))
        }
    }

    override fun getScores(id: String): Flow<StatesSchool> = flow {
        try {
            val schoolResult = schoolAPI.getScores(id)
            if (schoolResult.isSuccessful) {
                schoolResult.body()?.let {
                    emit(StatesSchool.ScoresSuccess(it))
                } ?: throw Exception("BODY IS NULL")
            } else {
                throw Exception("RESULT GOES TO FAILURE")
            }

        } catch (e: Exception) {
            emit(StatesSchool.ERROR(e))
        }
    }

}