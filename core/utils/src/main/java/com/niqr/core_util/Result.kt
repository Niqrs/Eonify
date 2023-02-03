package com.niqr.core_util

sealed class Result<out S, out E> { // Success, Error
    data class Success<out S> (val data: S): Result<S, Nothing>()
    data class Error<out E> (val data: E): Result<Nothing, E>()
}