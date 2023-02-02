package com.niqr.auth.ui.di

import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignInWithEmailHandler
import com.niqr.auth.ui.handlers.SignUpWithEmailHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthUiModule {

    @Provides
    fun provideGoogleAuthResultHandler(
        repo: AuthRepository
    ) = GoogleAuthResultHandler(
        repo = repo
    )

    @Provides
    fun provideSignUpWithEmailHandler(
        repo: AuthRepository
    ) = SignUpWithEmailHandler (
        repo = repo
    )

    @Provides
    fun provideSignInWithEmailHandler(
        repo: AuthRepository
    ) = SignInWithEmailHandler (
        repo = repo
    )
}