package tech.pixela.jetfm

@Suppress("SpellCheckingInspection")
object NewDependencies {
    object Kotlin {
        const val kotlinx_serialization_json =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${NewVersions.Kotlin.kotlinx_serialization_json}"
    }

    object Android {
        object Base {
            const val androidx_core =
                "androidx.core:core-ktx:${NewVersions.Android.Base.androidx_core}"
            const val androidx_lifecycle =
                "androidx.lifecycle:lifecycle-runtime-ktx:${NewVersions.Android.Base.androidx_lifecycle}"
        }

        object Jetpack {
            const val data_store =
                "androidx.datastore:datastore-preferences:${NewVersions.Android.Jetpack.data_store}"
            const val hilt_android =
                "com.google.dagger:hilt-android:${NewVersions.Android.Jetpack.hilt}"
            const val hilt_compiler =
                "com.google.dagger:hilt-compiler:${NewVersions.Android.Jetpack.hilt}"
            const val hilt_plugin =
                "com.google.dagger:hilt-android-gradle-plugin:${NewVersions.Android.Jetpack.hilt}"
            const val paging =
                "androidx.paging:paging-runtime:${NewVersions.Android.Jetpack.paging}"
        }

        object Compose {
            object Core {
                const val bom = "androidx.compose:compose-bom:${NewVersions.Android.Compose.Core.bom}"
                const val compiler = ""
                const val tooling_preview = "androidx.compose.ui:ui-tooling-preview"
                const val ui = "androidx.compose.ui:ui"
            }

            const val activity =
                "androidx.activity:activity-compose:${NewVersions.Android.Compose.activity}"
            const val hilt_navigation =
                "androidx.hilt:hilt-navigation-compose:${NewVersions.Android.Compose.hilt_navigation}"
            const val material3 =
                "androidx.compose.material3:material3:${NewVersions.Android.Compose.material3}"
            const val navigation =
                "androidx.navigation:navigation-compose:${NewVersions.Android.Compose.navigation}"
            const val paging =
                "androidx.paging:paging-compose:${NewVersions.Android.Compose.paging}"
            const val view_model =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${NewVersions.Android.Compose.view_model}"
        }
    }

    object ThirdParty {
        object Accompanist {
            const val flow_layout =
                "com.google.accompanist:accompanist-flowlayout:${NewVersions.ThirdParty.accompanist}"
            const val insets =
                "com.google.accompanist:accompanist-insets:${NewVersions.ThirdParty.accompanist}"
            const val pager =
                "com.google.accompanist:accompanist-pager:${NewVersions.ThirdParty.accompanist}"
            const val pager_indicators =
                "com.google.accompanist:accompanist-pager-indicators:${NewVersions.ThirdParty.accompanist}"
            const val system_ui =
                "com.google.accompanist:accompanist-systemuicontroller:${NewVersions.ThirdParty.accompanist}"
            const val swipe_refresh =
                "com.google.accompanist:accompanist-swiperefresh:${NewVersions.ThirdParty.accompanist}"
        }

        object Chucker {
            const val chucker_debug =
                "com.github.chuckerteam.chucker:library:${NewVersions.ThirdParty.chucker}"
            const val chucker_release =
                "com.github.chuckerteam.chucker:library-no-op:${NewVersions.ThirdParty.chucker}"
        }

        const val coil = "io.coil-kt:coil-compose:${NewVersions.ThirdParty.coil}"

        object MaterialMotion {
            const val core =
                "io.github.fornewid:material-motion-compose-core:${NewVersions.ThirdParty.material_motion}"
            const val navigation =
                "io.github.fornewid:material-motion-compose-navigation:${NewVersions.ThirdParty.material_motion}"
        }

        object SquareUp {
            const val okhttp = "com.squareup.okhttp3:okhttp:${NewVersions.ThirdParty.okhttp}"
            const val retrofit =
                "com.squareup.retrofit2:retrofit:${NewVersions.ThirdParty.retrofit}"
            const val retrofit_kotlinx_serialization =
                "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${NewVersions.ThirdParty.retrofit_kotlinx_serialization}"
        }
    }

    object Testing {
        const val espresso_androidx =
            "androidx.test.espresso:espresso-core:${NewVersions.Testing.espresso_androidx}"
        const val junit = "junit:junit:${NewVersions.Testing.junit}"
        const val junit_androidx =
            "androidx.test.ext:junit:${NewVersions.Testing.junit_androidx}"
        const val ui_test_manifest = "androidx.compose.ui:ui-test-manifest"
        const val ui_testing = "androidx.compose.ui:ui-test-junit4"
        const val ui_tooling = "androidx.compose.ui:ui-tooling"
    }
}