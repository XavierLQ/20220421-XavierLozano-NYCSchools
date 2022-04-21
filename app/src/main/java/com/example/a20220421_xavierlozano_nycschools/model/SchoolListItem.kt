package com.example.a20220421_xavierlozano_nycschools.model


import com.google.gson.annotations.SerializedName

data class SchoolListItem(
    @SerializedName("dbn")
    val dbn: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("location")
    val location: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String? = null,
    @SerializedName("school_email")
    val schoolEmail: String? = null,
    @SerializedName("school_name")
    val schoolName: String? = null,
    @SerializedName("school_sports")
    val schoolSports: String? = null,
    @SerializedName("total_students")
    val totalStudents: String? = null,
    @SerializedName("website")
    val website: String? = null,
    @SerializedName("zip")
    val zip: String? = null
)