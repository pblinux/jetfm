package gt.com.pixela.jetfm.data.models.response

import com.squareup.moshi.Json
import gt.com.pixela.jetfm.data.models.User
import gt.com.pixela.jetfm.data.models.common.Meta

data class Friends(
  @Json(name = "@attr") val meta: Meta,
  @Json(name = "user") val friends: List<User>
)