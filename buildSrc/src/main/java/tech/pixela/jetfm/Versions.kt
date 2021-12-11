package tech.pixela.jetfm

@Suppress("SpellCheckingInspection")
object Versions {
    object Kotlin {
        const val kotlinx_serialization_json = "1.3.1"

        object Testing {
            const val junit = "4.13.2"
        }
    }

    object Android {
        object Base {
            const val androidx_core = "1.7.0"
            const val androidx_lifecycle = "2.3.1"
        }

        object Jetpack {
            const val data_store = "1.0.0"
            const val hilt = "2.39.1"
            const val paging = "3.1.0"
        }

        object JetpackCompose {
            const val activity = "1.3.1"
            const val hilt_navigation = "1.0.0-beta01"
            const val compose = "1.1.0-beta03"
            const val material3 = "1.0.0-alpha01"
            const val navigation = "2.4.0-beta02"
            const val paging = "1.0.0-alpha14"
            const val view_model = "1.0.0-alpha07"
        }

        object Testing {
            const val espresso_androidx = "3.4.0"
            const val junit_androidx = "1.1.3"
        }
    }

    object ThirdParty {
        const val accompanist = "0.21.3-beta"
        const val chucker = "3.5.2"
        const val coil = "1.4.0"
        const val material_motion = "0.8.0-beta03"
        const val okhttp = "4.9.3"
        const val retrofit = "2.9.0"
        const val retrofit_kotlinx_serialization = "0.8.0"
    }
}