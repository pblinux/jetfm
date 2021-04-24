package gt.com.pixela.jetfm.data.models.common

import com.squareup.moshi.Json

data class Date(
  val uts: String,
  @Json(name = "#text") val date: String,
)