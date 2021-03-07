package com.mohsen.tvsample.data.remote.model

import com.google.gson.annotations.SerializedName

data class Solon(
    @SerializedName("solon_id")
    val solonId: String ,
    @SerializedName("name")
    val name: String ,
    @SerializedName("phone")
    val phone: String ,
    @SerializedName("image")
    val image: String ,
    @SerializedName("address")
    val address: String
)
data class SolonList(
    @SerializedName("datas")
    val datas : List<Solon>
)
data class SolonPaginate(
        @SerializedName("data")
        val data: List<Solon>,
        @SerializedName("meta")
        val metadata: MetaData
)