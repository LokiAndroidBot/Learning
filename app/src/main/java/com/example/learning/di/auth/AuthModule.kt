package com.example.learning.di.auth

import com.example.learning.data.api.ApiService
import com.example.learning.data.repository.auth.AuthRepository
import com.example.learning.data.repository.auth.AuthRepositoryImpl
import com.example.learning.domain.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepositoryImpl(apiService)
    }

    @Provides
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(authRepository)
    }
}
