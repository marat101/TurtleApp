package com.turtleteam.domain.model

data class NamesList(
    // Закрепленные группы\преподы
    val pinned: List<String>,

    // Остальные
    val groups: List<String>
)
