package br.iesb.mobile.firebaselogin.view.activity

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import br.iesb.mobile.firebaselogin.R


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
    }
}