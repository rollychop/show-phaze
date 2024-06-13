package com.brohit.show_phaze.data.repository

import com.brohit.show_phaze.data.data_source.local.data_store.UserDataStore
import com.brohit.show_phaze.data.data_source.local.room.dao.HeaderDao
import com.brohit.show_phaze.domain.model.user.UserModel
import com.brohit.show_phaze.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val headerDao: HeaderDao,
    private val userDataStore: UserDataStore,
) : AuthRepository {

    override suspend fun login(
        username: String,
        password: String
    ): Result<UserModel> = kotlin.runCatching {
        throw IllegalStateException("Not implemented")
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        username: String,
        mobileNumber: String,
    ): Result<UserModel> = kotlin.runCatching {
        throw IllegalStateException("Not implemented")
    }

    override suspend fun forgetPassword(username: String)
            : Result<Any> = kotlin.runCatching {
        throw IllegalStateException("Not implemented")
    }

    override suspend fun logout(): Result<Any> = kotlin.runCatching {


    }
}