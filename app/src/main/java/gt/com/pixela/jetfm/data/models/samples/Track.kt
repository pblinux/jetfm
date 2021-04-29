package gt.com.pixela.jetfm.data.models.samples

import com.squareup.moshi.Moshi
import gt.com.pixela.jetfm.data.models.RecentTrack

const val trackJson =
  "{ \"artist\": { \"mbid\": \"9a58fda3-f4ed-4080-a3a5-f457aac9fcdd\", \"#text\": \"Joy Division\" }, \"album\": { \"mbid\": \"\", \"#text\": \"The Best Of\" }, \"image\": [{ \"size\": \"small\", \"#text\": \"https:\\/\\/lastfm.freetls.fastly.net\\/i\\/u\\/34s\\/043311d565be4296bb13f299ba1f08de.jpg\" }, { \"size\": \"medium\", \"#text\": \"https:\\/\\/lastfm.freetls.fastly.net\\/i\\/u\\/64s\\/043311d565be4296bb13f299ba1f08de.jpg\" }, { \"size\": \"large\", \"#text\": \"https:\\/\\/lastfm.freetls.fastly.net\\/i\\/u\\/174s\\/043311d565be4296bb13f299ba1f08de.jpg\" }, { \"size\": \"extralarge\", \"#text\": \"https:\\/\\/lastfm.freetls.fastly.net\\/i\\/u\\/300x300\\/043311d565be4296bb13f299ba1f08de.jpg\" } ], \"streamable\": \"0\", \"date\": { \"uts\": \"1618671253\", \"#text\": \"17 Apr 2021, 14:54\" }, \"url\": \"https:\\/\\/www.last.fm\\/music\\/Joy+Division\\/_\\/Love+Will+Tear+Us+Apart\", \"name\": \"Love Will Tear Us Apart\", \"mbid\": \"00046764-a84c-3568-b8b1-6a8174f7ca0b\" }"

val SAMPLE_RECENT_TRACK: RecentTrack = Moshi.Builder().build().adapter(RecentTrack::class.java).fromJson(trackJson)!!
