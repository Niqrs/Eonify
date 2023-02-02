package com.niqr.eonify.di

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.niqr.auth.data.AuthRepositoryImpl
import com.niqr.auth.domain.AuthRepository
import com.niqr.auth.ui.handlers.GoogleAuthResultHandler
import com.niqr.auth.ui.handlers.SignInWithEmailHandler
import com.niqr.auth.ui.handlers.SignUpWithEmailHandler
import com.niqr.core.FirebaseSecretConstants
import com.niqr.profile.data.ProfileRepositoryImpl
import com.niqr.profile.domain.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule { //TODO: Manage hilt modules
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideGoogleSignInClient(
        app: Application
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(FirebaseSecretConstants.webClientId)
            .build()
        return GoogleSignIn.getClient(app, gso)
    }

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        db = db
    )

    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        googleSignInClient: GoogleSignInClient
    ): ProfileRepository = ProfileRepositoryImpl(
        auth = auth,
        googleClient = googleSignInClient
    )

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