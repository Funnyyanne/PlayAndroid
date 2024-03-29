package com.anne.play.logic.model

/**
 *
 * Author:AnneLo
 * Time:2023/10/2
 */
data class LoginModel(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Int>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String,
)
