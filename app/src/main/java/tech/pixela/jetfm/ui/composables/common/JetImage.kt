package tech.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import tech.pixela.jetfm.R

@Composable
fun JetImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    circular: Boolean = false,
) {
    Image(
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                error(R.drawable.fallback)
                if (circular) {
                    transformations(CircleCropTransformation())
                }
            },
        ),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
    )
}