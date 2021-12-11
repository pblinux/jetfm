package tech.pixela.jetfm.data.model.lastfm.base

import kotlinx.serialization.Serializable
import tech.pixela.jetfm.data.model.lastfm.common.Image
import tech.pixela.jetfm.data.model.lastfm.common.Registered

@Suppress("SpellCheckingInspection")
@Serializable
data class User(
  val age: String? = null,
  val bootstrap: String,
  val country: String,
  val gender: String? = null,
  val image: List<Image>,
  val name: String,
  val playcount: Int,
  val playlists: Int,
  val realname: String,
  val registered: Registered,
  val subscriber: Int,
  val url: String,
)