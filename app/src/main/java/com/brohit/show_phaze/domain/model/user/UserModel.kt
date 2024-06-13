package com.brohit.show_phaze.domain.model.user


data class UserModel(
    val name: String,
    val email: String,
    val mobileNumber: String,
    val username: String,
    val id: String,
    val loginId: String,
){
    private constructor() : this(
        name = "",
        email = "",
        mobileNumber = "",
        username = "",
        id = "",
        loginId = ""
    )

    companion object {
        private val INSTANCE by lazy { UserModel() }
        fun empty(): UserModel = INSTANCE
    }

    val alias: String = name.take(2).uppercase()




}