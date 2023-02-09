package com.niqr.profile.data.di

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
        firestore: FirebaseFirestore,
        googleSignInClient: GoogleSignInClient
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        firestore = firestore,
        googleClient = googleSignInClient
    )
}
