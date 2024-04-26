package com.example.delifood.Model

data class User(
    var username: String?,
    var email: String?,
    var password: String?,
    var imgProfile: String?
) {
    constructor() : this("", "", "", "")

}