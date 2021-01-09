package br.iesb.mobile.firebaselogin.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.iesb.mobile.firebaselogin.R
import br.iesb.mobile.firebaselogin.databinding.FragmentLoginBinding
import br.iesb.mobile.firebaselogin.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.result.observe(viewLifecycleOwner) { result ->
            when(result.status) {
                "success" -> {
                    Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                }
                else -> Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    fun login() {
        viewModel.login()
    }

    fun register() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun forgotPassword() {
        findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
    }

    fun signInGoogle() {
        viewModel.signInGoogle()
    }

}