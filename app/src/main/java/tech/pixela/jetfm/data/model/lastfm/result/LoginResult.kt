package tech.pixela.jetfm.data.model.lastfm.result

import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.base.Session

@Serializable
data class LoginResult(val session: Session)