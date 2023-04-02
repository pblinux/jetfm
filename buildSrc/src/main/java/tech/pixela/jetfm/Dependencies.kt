package tech.pixela.jetfm

@Suppress("SpellCheckingInspection")
object Dependencies {
    object Kotlin {
        const val kotlinx_serialization_json =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.kotlinx_serialization_json}"

        object Testing {
            const val junit = "junit:junit:${Versions.Kotlin.Testing.junit}"
        }
    }

    object Android {
        object Base {
            const val androidx_core =
                "androidx.core:core-ktx:${Versions.Android.Base.androidx_core}"
            const val androidx_lifecycle =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.Base.androidx_lifecycle}"
        }

        object Jetpack {
            const val data_store =
                "androidx.datastore:datastore-preferences:${Versions.Android.Jetpack.data_store}"
            const val hilt_android =
                "com.google.dagger:hilt-android:${Versions.Android.Jetpack.hilt}"
            const val hilt_compiler =
                "com.google.dagger:hilt-compiler:${Versions.Android.Jetpack.hilt}"
            const val hilt_plugin =
                "com.google.dagger:hilt-android-gradle-plugin:${Versions.Android.Jetpack.hilt}"
            const val paging =
                "androidx.paging:paging-runtime:${tech.pixela.jetfm.Versions.Android.Jetpack.paging}"
        }

        object JetpackCompose {
            const val activity =
                "androidx.activity:activity-compose:${Versions.Android.JetpackCompose.activity}"
            const val hilt_navigation =
                "androidx.hilt:hilt-navigation-compose:${Versions.Android.JetpackCompose.hilt_navigation}"
            const val material2 =
                "androidx.compose.material:material:${Versions.Android.JetpackCompose.material}"
            const val material3 =
                "androidx.compose.material3:material3:${Versions.Android.JetpackCompose.material3}"
            const val navigation =
                "androidx.navigation:navigation-compose:${Versions.Android.JetpackCompose.navigation}"
            const val paging =
                "androidx.paging:paging-compose:${tech.pixela.jetfm.Versions.Android.JetpackCompose.paging}"
            const val tooling_preview =
                "androidx.compose.ui:ui-tooling-preview:${Versions.Android.JetpackCompose.tooling}"
            const val ui = "androidx.compose.ui:ui:${Versions.Android.JetpackCompose.ui}"
            const val view_model =
                "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Android.JetpackCompose.view_model}"

            object Testing {
                const val ui_test_manifest =
                    "androidx.compose.ui:ui-test-manifest:${Versions.Android.JetpackCompose.tooling}"
                const val ui_testing =
                    "androidx.compose.ui:ui-test-junit4:${Versions.Android.JetpackCompose.tooling}"
                const val ui_tooling =
                    "androidx.compose.ui:ui-tooling:${Versions.Android.JetpackCompose.tooling}"
            }
        }

        object Testing {
            const val junit_androidx =
                "androidx.test.ext:junit:${Versions.Android.Testing.junit_androidx}"
            const val espresso_androidx =
                "androidx.test.espresso:espresso-core:${Versions.Android.Testing.espresso_androidx}"
        }
    }

    object ThirdParty {
        object Accompanist {
            const val flow_layout =
                "com.google.accompanist:accompanist-flowlayout:${Versions.ThirdParty.accompanist}"
            const val insets =
                "com.google.accompanist:accompanist-insets:${Versions.ThirdParty.accompanist}"
            const val pager =
                "com.google.accompanist:accompanist-pager:${Versions.ThirdParty.accompanist}"
            const val pager_indicators =
                "com.google.accompanist:accompanist-pager-indicators:${Versions.ThirdParty.accompanist}"
            const val system_ui =
                "com.google.accompanist:accompanist-systemuicontroller:${Versions.ThirdParty.accompanist}"
            const val swipe_refresh =
                "com.google.accompanist:accompanist-swiperefresh:${Versions.ThirdParty.accompanist}"
        }

        object Chucker {
            const val chucker_debug =
                "com.github.chuckerteam.chucker:library:${Versions.ThirdParty.chucker}"
            const val chucker_release =
                "com.github.chuckerteam.chucker:library-no-op:${Versions.ThirdParty.chucker}"
        }

        const val coil = "io.coil-kt:coil-compose:${Versions.ThirdParty.coil}"

        object MaterialMotion {
            const val core =
                "io.github.fornewid:material-motion-compose-core:${Versions.ThirdParty.material_motion}"
            const val navigation =
                "io.github.fornewid:material-motion-compose-navigation:${Versions.ThirdParty.material_motion}"
        }

        object SquareUp {
            const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.ThirdParty.okhttp}"
            const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.ThirdParty.retrofit}"
            const val retrofit_kotlinx_serialization =
                "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.ThirdParty.retrofit_kotlinx_serialization}"
        }
    }
}