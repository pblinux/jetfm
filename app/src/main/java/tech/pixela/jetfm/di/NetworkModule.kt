package tech.pixela.jetfm.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter

@ExperimentalSerializationApi
@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {
    private val contentType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    fun providesJsonConverter(): Converter.Factory {
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun providesChuckerClient(
        @ApplicationContext applicationContext: Context
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(applicationContext)
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    fun providesHttpClient(
        @ApplicationContext applicationContext: Context,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(providesChuckerClient(applicationContext))
            .build()
    }
}