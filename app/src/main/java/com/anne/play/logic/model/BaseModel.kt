package com.anne.play.logic.model

/**
 *
 * Author:AnneLo
 * Time:2023/9/12
 */
data class BaseModel<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)