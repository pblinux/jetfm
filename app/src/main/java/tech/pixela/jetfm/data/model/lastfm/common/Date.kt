package tech.pixela.jetfm.data.model.lastfm.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val uts: String,
    @SerialName("#text") val date: String
)
