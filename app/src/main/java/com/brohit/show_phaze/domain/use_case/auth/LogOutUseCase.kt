package com.brohit.show_phaze.domain.use_case.auth

import com.brohit.show_phaze.data.data_source.local.data_store.UserDataStore
import com.brohit.show_phaze.data.data_source.local.room.dao.HeaderDao
import com.brohit.show_phaze.data.data_source.local.room.dao.SessionDao
import com.brohit.show_phaze.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionDao: SessionDao,
    private val headerDao: HeaderDao,
    private val ioDispatcher: CoroutineDispatcher,
    private val userDataStore: UserDataStore,
) {
    suspend operator fun invoke(): Result<Unit> = authRepository.logout().map {
        withContext(ioDispatcher) {
            sessionDao.clearAll()
            headerDao.clearAll()
            userDataStore.clearUser()
        }
    }
}