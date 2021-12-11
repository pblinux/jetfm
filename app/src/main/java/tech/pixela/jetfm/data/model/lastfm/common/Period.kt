package tech.pixela.jetfm.data.model.lastfm.common

enum class Period(val value: String) {
    Overall("overall"),
    Weekly("7day"),
    Monthly("1month"),
    Trimester("3month"),
    Biannual("6month"),
    Yearly("12month"),
}