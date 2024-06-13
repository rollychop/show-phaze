package com.brohit.show_phaze.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.brohit.show_phaze.common.Constant
import com.brohit.show_phaze.data.data_source.local.room.AppDb
import com.brohit.show_phaze.data.data_source.local.room.dao.HeaderDao
import com.brohit.show_phaze.data.data_source.local.room.dao.SessionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesInDB(@ApplicationContext context: Context): AppDb {
        return Room.databaseBuilder(
            context,
            AppDb::class.java,
            "myapp.db"
        ).build()
    }

    @Provides
    fun providesSessionDao(
        appDb: AppDb
    ): SessionDao {
        return appDb.sessionDao()
    }

    @Provides
    fun providesHeadersDao(
        appDb: AppDb
    ): HeaderDao {
        return appDb.headerDao()
    }




    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO


    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext appContext: Context,
        ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, Constant.USER_PREFERENCES)),
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(Constant.USER_PREFERENCES) }
        )
    }


}