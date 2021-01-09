package br.iesb.mobile.firebaselogin.repository

import android.content.Context
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.domain.LoginResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LoginRepository(private val context: Context) {
    private val auth = Firebase.auth

    suspend fun login(email: String, pass:String): LoginResult = suspendCoroutine { nextStep ->
        println("login")
        val operation = auth.signInWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult("success", context.getString(R.string.login_success))
            } else {
                LoginResult("fail", op.exception?.localizedMessage)
            }
            nextStep.resume(res)
        }
    }

    suspend fun register(email: String, pass: String): LoginResult = suspendCoroutine { nextStep ->
        println("createUserWithEmailAndPassword")
        val operation =  auth.createUserWithEmailAndPassword(email, pass)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult("success", context.getString(R.string.login_success))
            } else {
                LoginResult("fail", op.exception?.localizedMessage)
            }
            nextStep.resume(res)
        }
    }

    suspend fun forgot(email: String): LoginResult = suspendCoroutine { nextStep ->
        println("sendPasswordResetEmail")
        val operation =  auth.sendPasswordResetEmail(email)
        operation.addOnCompleteListener { op ->
            val res = if (op.isSuccessful) {
                LoginResult("success", context.getString(R.string.login_success))
            } else {
                LoginResult("fail", op.exception?.localizedMessage)
            }
            nextStep.resume(res)
        }
    }

    suspend fun signInGoogle(): LoginResult = suspendCoroutine { nextStep ->

        val credential = GoogleAuthProvider.getCredential(GoogleAuthProvider.GOOGLE_SIGN_IN_METHOD,null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener{ op ->
                    val res = if (op.isSuccessful) {
                        LoginResult("success", context.getString(R.string.login_success))
                    } else {
                        LoginResult("fail", op.exception?.localizedMessage)
                    }
                    nextStep.resume(res)
                }
    }

}