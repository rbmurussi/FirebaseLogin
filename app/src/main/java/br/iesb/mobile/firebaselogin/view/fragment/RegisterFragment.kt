package br.iesb.mobile.firebaselogin.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.iesb.mobile.firebaselogin.databinding.FragmentRegisterBinding
import br.iesb.mobile.firebaselogin.viewmodel.LoginViewModel

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    fun register() {
        viewModel.register()
    }

}