package com.example.domain.commons

sealed class Result<T>
class Success<T>(val value: T) : Result<T>()
class Failure<T>(val message: String? = null) : Result<T>()