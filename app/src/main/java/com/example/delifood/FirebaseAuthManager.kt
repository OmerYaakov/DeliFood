package com.example.delifood

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult

class FirebaseAuthManager {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerUser(email: String, password: String, completion: (Result<AuthResult>) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result != null) {
                        completion(Result.success(result))
                    } else {
                        completion(Result.failure(Exception("Registration failed")))
                    }
                } else {
                    completion(Result.failure(task.exception ?: Exception("Registration failed")))
                }
            }
    }

    fun loginUser(email: String, password: String, completion: (Result<AuthResult>) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result != null) {
                        Log.d("FirebaseAuthManager", "Login successful")
                        completion(Result.success(result))
                    } else {
                        completion(Result.failure(Exception("Login failed")))
                    }
                } else {
                    completion(Result.failure(task.exception ?: Exception("Login failed")))
                }
            }
    }

    fun logoutUser() {
        firebaseAuth.signOut()
    }

    fun resetPassword(email: String, completion: (Result<Void?>) -> Unit) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    completion(Result.success(null))
                } else {
                    completion(Result.failure(task.exception ?: Exception("Password reset failed")))
                }
            }
    }

    fun changePassword(password: String, completion: (Result<Void?>) -> Unit) {
        firebaseAuth.currentUser?.updatePassword(password)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    completion(Result.success(null))
                } else {
                    completion(Result.failure(task.exception ?: Exception("Password change failed")))
                }
            }
    }

    fun changeEmail(email: String, completion: (Result<Void?>) -> Unit) {
        firebaseAuth.currentUser?.verifyBeforeUpdateEmail(email)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    completion(Result.success(null))
                } else {
                    completion(Result.failure(task.exception ?: Exception("Email change failed")))
                }
            }
    }


}