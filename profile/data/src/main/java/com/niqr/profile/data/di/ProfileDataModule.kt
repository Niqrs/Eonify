package com.niqr.profile.data.di

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.niqr.profile.data.ProfileRepositoryImpl
import com.niqr.profile.domain.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileDataModule {
    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        googleSignInClient: GoogleSignInClient
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        googleClient = googleSignInClient
    )
}