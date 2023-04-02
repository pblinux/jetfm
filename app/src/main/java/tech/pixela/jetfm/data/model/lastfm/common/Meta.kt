package tech.pixela.jetfm.data.model.lastfm.common

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val page: Int,
    val perPage: Int,
    val user: String,
    val total: Int,
    val totalPages: Int
)
