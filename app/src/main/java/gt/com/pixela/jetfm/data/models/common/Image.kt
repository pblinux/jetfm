package gt.com.pixela.jetfm.data.models.common

import com.squareup.moshi.Json

data class Image(val size: String, @Json(name = "#text") val url: String)