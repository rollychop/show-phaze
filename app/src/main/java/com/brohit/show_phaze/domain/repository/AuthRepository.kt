package com.brohit.show_phaze.domain.repository

import com.brohit.show_phaze.domain.model.user.UserModel

interface AuthRepository {

    suspend fun login(username: String, password: String): Result<UserModel>

    suspend fun register(
        email: String,
        password: String,
        name: String,
        username: String,
        mobileNumber: String
    ): Result<UserModel>

    suspend fun forgetPassword(username: String): Result<Any>

    suspend fun logout(): Result<Any>

}