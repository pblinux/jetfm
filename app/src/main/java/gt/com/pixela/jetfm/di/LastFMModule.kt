package gt.com.pixela.jetfm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gt.com.pixela.jetfm.data.source.LastfmApiClient

@Module
@InstallIn(ViewModelComponent::class)
object LastFMModule {

  @Provides
  fun provideLastFMService(): LastfmApiClient {
    return LastfmApiClient()
  }
}