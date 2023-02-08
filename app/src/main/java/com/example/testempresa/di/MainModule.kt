package com.example.testempresa.di

import android.content.Context
import androidx.room.Room
import com.example.testempresa.data.local.CarDao
import com.example.testempresa.data.local.CarDatabase
import com.example.testempresa.data.remote.ServiceApi
import com.example.testempresa.repository.BaseRepository
import com.example.testempresa.repository.RepositoryImp
import com.example.testempresa.utis.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun provideInstanceToDB(@ApplicationContext context: Context) : CarDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            CarDatabase::class.java,
            "Car"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCarDao(database: CarDatabase) : CarDao = database.getInstance()

    @Provides
    @Singleton
    fun provideServiceApi() : ServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoryImp(api : ServiceApi, dao : CarDao) : BaseRepository {
        return RepositoryImp(api, dao)
    }

}