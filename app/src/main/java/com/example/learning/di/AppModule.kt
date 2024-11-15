package com.example.learning.di

import com.example.learning.data.api.ApiService
import com.example.learning.data.repository.UserRepository
import com.example.learning.data.repository.UserRepositoryImpl
import com.example.learning.domain.FetchUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder().baseUrl("https://randomuser.me/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    fun provideFetchUserUseCase(userRepository: UserRepository): FetchUserUseCase {
        return FetchUserUseCase(userRepository)
    }
}
