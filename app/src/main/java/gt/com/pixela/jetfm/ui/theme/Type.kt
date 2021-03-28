package gt.com.pixela.jetfm.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import gt.com.pixela.jetfm.R

val Barlow = FontFamily(
  Font(R.font.barlow_black, FontWeight.Black),
  Font(R.font.barlow_blackitalic, FontWeight.Black, style = FontStyle.Italic),
  Font(R.font.barlow_bold, FontWeight.Bold),
  Font(R.font.barlow_bolditalic, FontWeight.Bold, style = FontStyle.Italic),
  Font(R.font.barlow_extrabold, FontWeight.ExtraBold),
  Font(R.font.barlow_extrabolditalic, FontWeight.ExtraBold, style = FontStyle.Italic),
  Font(R.font.barlow_extralight, FontWeight.ExtraLight),
  Font(R.font.barlow_extralightitalic, FontWeight.ExtraLight, style = FontStyle.Italic),
  Font(R.font.barlow_italic, FontWeight.Normal, style = FontStyle.Italic),
  Font(R.font.barlow_light, FontWeight.Light),
  Font(R.font.barlow_lightitalic, FontWeight.Light, style = FontStyle.Italic),
  Font(R.font.barlow_medium, FontWeight.Medium),
  Font(R.font.barlow_mediumitalic, FontWeight.Medium, style = FontStyle.Italic),
  Font(R.font.barlow_regular, FontWeight.Normal),
  Font(R.font.barlow_semibold, FontWeight.SemiBold),
  Font(R.font.barlow_semibolditalic, FontWeight.SemiBold, style = FontStyle.Italic),
  Font(R.font.barlow_thin, FontWeight.Thin),
  Font(R.font.barlow_thinitalic, FontWeight.Thin, style = FontStyle.Italic),
)

val Typography = Typography(
  h1 = TextStyle(
    fontSize = 102.sp,
    fontWeight = FontWeight.W300,
    letterSpacing = (-1.5).sp,
    fontFamily = Barlow
  ),
  h2 = TextStyle(
    fontSize = 64.sp,
    fontWeight = FontWeight.W300,
    letterSpacing = (-0.5).sp,
    fontFamily = Barlow
  ),
  h3 = TextStyle(
    fontSize = 51.sp,
    fontWeight = FontWeight.W400,
    fontFamily = Barlow
  ),
  h4 = TextStyle(
    fontSize = 36.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 0.25.sp,
    fontFamily = Barlow
  ),
  h5 = TextStyle(
    fontSize = 25.sp,
    fontWeight = FontWeight.W400,
    fontFamily = Barlow
  ),
  h6 = TextStyle(
    fontSize = 21.sp,
    fontWeight = FontWeight.W500,
    letterSpacing = 0.15.sp,
    fontFamily = Barlow
  ),
  subtitle1 = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 0.15.sp,
    fontFamily = Barlow
  ),
  subtitle2 = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
    letterSpacing = 0.1.sp,
    fontFamily = Barlow
  ),
  body1 = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 0.5.sp,
    fontFamily = Barlow
  ),
  body2 = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 0.25.sp,
    fontFamily = Barlow
  ),
  button = TextStyle(
    fontSize = 15.sp,
    fontWeight = FontWeight.W500,
    letterSpacing = 1.25.sp,
    fontFamily = Barlow
  ),
  caption = TextStyle(
    fontSize = 13.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 0.4.sp,
    fontFamily = Barlow
  ),
  overline = TextStyle(
    fontSize = 11.sp,
    fontWeight = FontWeight.W400,
    letterSpacing = 1.5.sp,
    fontFamily = Barlow
  ),
)