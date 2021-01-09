package br.iesb.mobile.firebaselogin.domain

import com.google.gson.annotations.SerializedName

data class ItemResult(
    @SerializedName("userId")
    var userId : String,
    @SerializedName("id")
    var id : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("body")
    var body : String
)