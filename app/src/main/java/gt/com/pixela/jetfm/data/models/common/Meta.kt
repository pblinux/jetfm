package gt.com.pixela.jetfm.data.models.common

data class Meta(
  val page: Int,
  val perPage: Int,
  val user: String,
  val total: Int,
  val totalPages: Int
)