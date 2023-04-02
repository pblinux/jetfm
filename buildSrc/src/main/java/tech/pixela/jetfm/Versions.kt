package tech.pixela.jetfm

@Suppress("SpellCheckingInspection")
object Versions {
    object Kotlin {
        const val kotlinx_serialization_json = "1.3.2"

        object Testing {
            const val junit = "4.13.2"
        }
    }

    object Android {
        object Base {
            const val androidx_core = "1.7.0"
            const val androidx_lifecycle = "2.5.1"
        }

        object Jetpack {
            const val data_store = "1.0.0"
            const val hilt = "2.43.1"
            const val paging = "3.1.1"
        }

        object JetpackCompose {
            const val activity = "1.5.1"
            const val compiler = "1.2.0"
            const val hilt_navigation = "1.0.0"
            const val material = "1.2.0"
            const val material3 = "1.0.0-alpha15"
            const val navigation = "2.5.1"
            const val paging = "1.0.0-alpha15"
            const val tooling = "1.2.0"
            const val ui = "1.2.0"
            const val view_model = "2.5.1"
        }

        object Testing {
            const val espresso_androidx = "3.4.0"
            const val junit_androidx = "1.1.3"
        }
    }

    object ThirdParty {
        const val accompanist = "0.25.0"
        const val chucker = "3.5.2"
        const val coil = "2.1.0"
        const val material_motion = "0.9.0"
        const val okhttp = "4.9.3"
        const val retrofit = "2.9.0"
        const val retrofit_kotlinx_serialization = "0.8.0"
    }
}