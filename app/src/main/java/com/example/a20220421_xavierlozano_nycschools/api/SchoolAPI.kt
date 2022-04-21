package com.example.a20220421_xavierlozano_nycschools.api

import com.example.a20220421_xavierlozano_nycschools.model.SchoolListItem
import com.example.a20220421_xavierlozano_nycschools.model.ScoreDetailsItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolAPI {

    @GET(PATH_URL)
    suspend fun getSchools(
    ): Response<List<SchoolListItem>>

    @GET(PATH_SCORE_URL)
    suspend fun getScores(
        @Query(QUERY_SHCOOL_ID) id: String
    ): Response<List<ScoreDetailsItem>>

    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val PATH_URL = "s3k6-pzi2.json"
        private const val PATH_SCORE_URL = "f9bf-2cp4.json"
        private const val QUERY_SHCOOL_ID = "dbn"
        //https://data.cityofnewyork.us/resource/f9bf-2cp4.json?dbn=27Q475
    }

}