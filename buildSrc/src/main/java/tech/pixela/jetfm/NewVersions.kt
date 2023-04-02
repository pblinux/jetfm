package tech.pixela.jetfm

object NewVersions {
    object Kotlin {
        const val kotlinx_serialization_json = "1.5.0"
    }

    object Android {
        object Base {
            const val androidx_core = "1.9.0"
            const val androidx_lifecycle = "2.6.1"
        }

        object Jetpack {
            const val data_store = "1.0.0"
            const val hilt = "2.44"
            const val paging = "3.1.1"
        }

        object Compose {
            object Core {
                const val bom = "2023.03.00"
                const val compiler = "1.4.4"
            }

            const val activity = "1.7.0"
            const val hilt_navigation = "1.0.0"
            const val material3 = "1.0.1"
            const val navigation = "2.5.3"
            const val paging = "1.0.0-alpha18"
            const val view_model = "2.6.1"
        }
    }

    @Suppress("SpellCheckingInspection")
    object ThirdParty {
        const val accompanist = "0.30.0"
        const val chucker = "3.5.2"
        const val coil = "2.3.0"
        const val material_motion = "0.11.1"
        const val okhttp = "4.10.0"
        const val retrofit = "2.9.0"
        const val retrofit_kotlinx_serialization = "0.8.0"
    }

    object Testing {
        const val espresso_androidx = "3.4.0"
        const val junit = "4.13.2"
        const val junit_androidx = "1.1.5"
    }
}