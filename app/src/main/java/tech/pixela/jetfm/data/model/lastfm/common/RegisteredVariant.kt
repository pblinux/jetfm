package tech.pixela.jetfm.data.model.lastfm.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisteredVariant(
    @SerialName("unixtime") val unixTime: Int,
    @SerialName("#text") val timestamp: String
)
