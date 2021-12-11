package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.Serializable

@Serializable
data class Session(val subscriber: Int, val name: String, val key: String)