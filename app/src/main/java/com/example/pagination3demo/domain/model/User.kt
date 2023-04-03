package com.example.pagination3demo.domain.model

import androidx.room.Embedded
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class User(
    @SerialName("links")
    @Embedded
    val userLinks: UserLinks,
    val username: String
)
