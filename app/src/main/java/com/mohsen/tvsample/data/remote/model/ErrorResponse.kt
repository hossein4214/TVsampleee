package com.mohsen.tvsample.data.remote.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String ,
    @SerializedName("error")
    val error: String ,
    @SerializedName("errors")
    val errors: RegisterationError
)