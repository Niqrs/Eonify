package com.niqr.core_util

fun String.filterWhitespaces() = this.filter { !it.isWhitespace() }