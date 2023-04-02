package tech.pixela.jetfm.ui.composables.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
private fun JetSpace(size: Double) {
    Spacer(
        modifier = Modifier
            .height(size.dp)
            .width(size.dp)
    )
}

@Composable
fun XSmallJetSpace() {
    JetSpace(size = 4.0)
}

@Composable
fun SmallJetSpace() {
    JetSpace(size = 8.0)
}

@Composable
fun MediumJetSpace() {
    JetSpace(size = 14.0)
}

@Composable
fun LargeJetSpace() {
    JetSpace(size = 16.0)
}

@Composable
fun CustomJetSpace(size: Double) {
    JetSpace(size)
}
