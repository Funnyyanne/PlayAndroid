package com.anne.play.logic.network.service

import com.anne.play.logic.model.BaseModel
import com.anne.play.logic.model.LoginModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
interface LoginService {

    @GET("user/login")
    suspend fun getLogout(): BaseModel<Any>

    @GET("user/register")
    suspend fun getRegister(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String,
    ): BaseModel<LoginModel>

    @GET("user/logout/json")
    suspend fun getLogin(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseModel<LoginModel>
}
