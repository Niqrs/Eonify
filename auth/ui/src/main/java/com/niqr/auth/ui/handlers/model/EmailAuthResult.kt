package com.niqr.auth.ui.handlers.model

import arrow.core.Either

typealias EmailSignUpResult = Either<String, Unit>
typealias EmailSignInResult = Either<String, Unit>