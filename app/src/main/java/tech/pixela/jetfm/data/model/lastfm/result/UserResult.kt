package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.User

@Serializable
data class UserResult(val user: User)