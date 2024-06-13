package com.brohit.show_phaze.data.data_source.local.data_store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.brohit.show_phaze.domain.model.user.UserModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private companion object {
        val NAME_KEY = stringPreferencesKey("name.key")
        val EMAIL_KEY = stringPreferencesKey("email.key")
        val USERNAME_KEY = stringPreferencesKey("username.key")
        val MOBILE_NUMBER_KEY = stringPreferencesKey("mobile.number.key")
        val ID_KEY = stringPreferencesKey("id.key")
        val LOGIN_ID_KEY = stringPreferencesKey("login.id.key")
    }

    suspend fun clearUser() {
        dataStore.edit { it.clear() }
    }

    suspend fun clearAndSaveUserInfo(
        userModel: UserModel,
    ) {
        clearAndSaveUserInfo(
            userModel.name,
            userModel.email,
            userModel.mobileNumber,
            userModel.username,
            userModel.id,
            userModel.loginId
        )
    }

    suspend fun clearAndSaveUserInfo(
        name: String,
        email: String,
        mobileNumber: String,
        username: String,
        id: String,
        loginId: String,
    ) {
        dataStore.edit {
            it.clear()
            it[NAME_KEY] = name
            it[EMAIL_KEY] = email
            it[USERNAME_KEY] = username
            it[MOBILE_NUMBER_KEY] = mobileNumber
            it[ID_KEY] = id
            it[LOGIN_ID_KEY] = loginId
        }
    }

    suspend fun getUserOrThrow(): UserModel {
        val userModelFlow = getUserFlow()
        return userModelFlow.first()
    }

    suspend fun getUserOrNull(): UserModel? = getUserFlow().firstOrNull()
    fun getUserFlow() = dataStore.data
        .catch {
            if (it is IOException) {
                emit(emptyPreferences())
            }
        }
        .map { preferences ->
            UserModel(
                name = preferences[NAME_KEY] ?: "",
                email = preferences[EMAIL_KEY] ?: "",
                username = preferences[USERNAME_KEY] ?: "",
                mobileNumber = preferences[MOBILE_NUMBER_KEY] ?: "",
                id = preferences[ID_KEY] ?: "",
                loginId = preferences[LOGIN_ID_KEY] ?: ""
            )
        }


}