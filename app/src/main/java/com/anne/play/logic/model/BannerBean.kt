package com.anne.play.logic.model

import com.zj.banner.model.BaseBannerBean

/**
 *
 * Author:AnneLo
 * Time:2023/9/12
 */
data class BannerBean(
    val uid: Int = 0,
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String,
    var filePath: String = "",
    override var data: String?
) : BaseBannerBean()
