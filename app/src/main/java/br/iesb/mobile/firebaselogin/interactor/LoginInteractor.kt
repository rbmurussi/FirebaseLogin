package br.iesb.mobile.firebaselogin.interactor

import android.content.Context
import android.util.Patterns
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.domain.LoginResult
import br.iesb.mobile.firebaselogin.repository.LoginRepository
import java.lang.Exception

class LoginInteractor(private val context: Context) {

    private val repository = LoginRepository(context)

    suspend fun login(email: String?, pass: String?): LoginResult {
        if (email.isNullOrBlank()) {
            throw Exception(context.getString(R.string.email_required))
        }

        if (pass.isNullOrBlank()) {
            throw Exception(context.getString(R.string.pass_required))
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            throw Exception(context.getString(R.string.invalid_email))
        }

        if (pass.length < 6){
            throw Exception(context.getString(R.string.invalid_password_size))
        }

        return repository.login(email, pass)
    }

    suspend fun register(email: String, pass: String): LoginResult {
        return repository.register(email, pass)
    }

    suspend fun forgot(email: String): LoginResult {
        return repository.forgot(email)
    }

    suspend fun signInGoogle(): LoginResult {
        return repository.signInGoogle()
    }

}