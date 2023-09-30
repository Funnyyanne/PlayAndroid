package com.anne.play.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * Author:AnneLo
 * Time:2023/9/14
 */
object ServiceCreator {
    private const val BASE_URL = "https://www.wanandroid.com/"
    private fun create(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
    fun <T> create(service: Class<T>): T = create().create(service)
}
