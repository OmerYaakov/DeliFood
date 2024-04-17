package com.example.delifood.Model

class User {
    var username: String? = null
    var email: String? = null
    var password: String? = null
    var imgProfile: String? = null
    constructor(username: String?, email: String?, password: String?, imgProfile: String?) {
        this.username = username
        this.email = email
        this.password = password
        this.imgProfile = imgProfile
    }
    constructor() {}
}