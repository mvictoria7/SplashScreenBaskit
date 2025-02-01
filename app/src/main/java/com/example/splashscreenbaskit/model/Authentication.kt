package com.example.splashscreenbaskit.model

import com.google.gson.annotations.SerializedName

data class  AuthenticationLogin(

    val username: String,
    val password: String
)

data class AuthenticationLoginResponse(
    @SerializedName("access_token")
    val accessToken: String
)