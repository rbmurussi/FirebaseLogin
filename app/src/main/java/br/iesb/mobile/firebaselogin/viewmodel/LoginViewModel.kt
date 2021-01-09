package br.iesb.mobile.firebaselogin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.iesb.mobile.firebaselogin.domain.LoginResult
import br.iesb.mobile.firebaselogin.interactor.LoginInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LoginViewModel(
    val app: Application
) : AndroidViewModel(app), CoroutineScope {

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    private val interactor = LoginInteractor(app.applicationContext)

    val email = MutableLiveData("")
    val pass = MutableLiveData("")
    val result = MutableLiveData<LoginResult>()

    fun login() {
        val e = email.value
        val p = pass.value

        launch {
            try {
                result.value = interactor.login(e, p)
            } catch (e: Exception) {
                val res = LoginResult("fail", e.localizedMessage)
                result.value = res
            }
        }
    }

    fun register() {
        // TODO: DO IT YOURSELF
        val email = email.value
        val pass = pass.value
        launch {
            try {
                if(email != null && pass != null)
                    interactor.register(email, pass)
            } catch (e: Exception) {
                val res = LoginResult("fail", e.localizedMessage)
                result.value = res
            }
        }
    }

    fun forgotPassword() {
        // TODO: DO IT YOURSELF
        val email = email.value
        launch {
            try {
                if(email != null)
                interactor.forgot(email)
            } catch (e: Exception) {
                val res = LoginResult("fail", e.localizedMessage)
                result.value = res
            }
        }
    }

    fun signInGoogle() {
        launch {
            try {
                result.value = interactor.signInGoogle()
            } catch (e: Exception) {
                val res = LoginResult("fail", e.localizedMessage)
                result.value = res
            }
        }
    }
}