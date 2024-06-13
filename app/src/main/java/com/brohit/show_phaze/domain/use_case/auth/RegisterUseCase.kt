package com.brohit.show_phaze.domain.use_case.auth

import com.brohit.show_phaze.domain.model.user.UserModel
import com.brohit.show_phaze.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        password: String,
        email: String,
        name: String,
        mobileNumber: String,
    ): Result<UserModel> =
        authRepository.register(email, password, name, username, mobileNumber)
}