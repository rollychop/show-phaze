package com.brohit.show_phaze.domain.use_case.session

import com.brohit.show_phaze.data.data_source.local.data_store.UserDataStore
import com.brohit.show_phaze.domain.model.session.SessionRoute
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckLoggedInSession @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val userDataStore: UserDataStore,
) {
    suspend operator fun invoke(): SessionRoute {
        return withContext(ioDispatcher) {
            SessionRoute(false)
        }
    }

}