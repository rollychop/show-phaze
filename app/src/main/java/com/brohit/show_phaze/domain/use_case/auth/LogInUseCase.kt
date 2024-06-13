package com.brohit.show_phaze.domain.use_case.auth

import com.brohit.show_phaze.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String) =
        authRepository.login(username, password)
}