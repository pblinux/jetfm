package tech.pixela.jetfm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.pixela.jetfm.data.source.local.datastore.DataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesDataStoreManager(
        @ApplicationContext applicationContext: Context
    ): DataStoreManager {
        return DataStoreManager(applicationContext)
    }
}