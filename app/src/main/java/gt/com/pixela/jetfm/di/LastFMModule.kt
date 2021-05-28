package gt.com.pixela.jetfm.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import gt.com.pixela.jetfm.R
import gt.com.pixela.jetfm.data.source.LastfmApiClient

@Module
@InstallIn(ViewModelComponent::class)
object LastFMModule {

  @Provides
  fun provideLastFMService(@ApplicationContext context: Context): LastfmApiClient {
    return LastfmApiClient(
      context.getString(R.string.lastfm_key),
      context.getString(R.string.lastfm_secret)
    )
  }
}