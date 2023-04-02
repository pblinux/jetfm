package tech.pixela.jetfm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit
import tech.pixela.jetfm.data.repository.LastfmRepository
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmService
import tech.pixela.jetfm.data.source.remote.lastfm.LastfmSource
import tech.pixela.jetfm.di.NetworkModule.providesHttpClient
import tech.pixela.jetfm.di.NetworkModule.providesJsonConverter
import tech.pixela.jetfm.utils.general.Constants

@ExperimentalSerializationApi
@Module
@InstallIn(ViewModelComponent::class)
object LastfmModule {
    @Provides
    fun providesRetrofit(
        @ApplicationContext applicationContext: Context
    ): Retrofit {
        return Retrofit.Builder()
            .client(providesHttpClient(applicationContext))
            .baseUrl(Constants.BaseUrl)
            .addConverterFactory(providesJsonConverter())
            .build()
    }

    @Provides
    fun providesLastfmSource(
        @ApplicationContext applicationContext: Context
    ): LastfmSource {
        return LastfmSource(
            providesRetrofit(applicationContext).create(LastfmService::class.java),
            Constants.LastfmKey,
            Constants.LastfmSecret
        )
    }

    @Provides
    fun providesLastfmRepository(
        @ApplicationContext applicationContext: Context
    ): LastfmRepository {
        return LastfmRepository(providesLastfmSource(applicationContext))
    }
}
