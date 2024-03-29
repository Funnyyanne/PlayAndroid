package com.anne.play.logic.model // ktlint-disable filename

data class ClassifyModel(
    val uid: Int,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
