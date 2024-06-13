package com.brohit.show_phaze.domain.use_case.user

import com.brohit.show_phaze.data.data_source.local.data_store.UserDataStore
import com.brohit.show_phaze.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLoggedUser @Inject constructor(
    private val dataStore: UserDataStore
) {
    operator fun invoke(): Flow<UserModel> = dataStore.getUserFlow()
}