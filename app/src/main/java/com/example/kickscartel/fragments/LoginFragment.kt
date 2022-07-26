package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.kickscartel.R
import com.example.kickscartel.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    lateinit var authentication: FirebaseAuth

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container,false)
        val root: View = binding.root
        authentication = Firebase.auth
        binding.loginRegisterButton.setOnClickListener { view ->
            if (validateFields(binding)) {
                registerUser(binding)
            } else {
                Toast.makeText(this.context, "Complete required information", Toast.LENGTH_SHORT).show()
            }
        }

        binding.loginSignInButton.setOnClickListener { view ->
            if (validateFields(binding)) {
                logIn(binding)
            } else {
                Toast.makeText(this.context, "Complete required information", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    private fun validateFields(binding: FragmentLoginBinding): Boolean {
        val mailFilled: Boolean = !binding.loginEmailInputField.text.isEmpty()
        val passwordFilled: Boolean = !binding.loginPasswordInputField.text.isEmpty()

        return mailFilled && passwordFilled
    }

    fun logIn(binding: FragmentLoginBinding) {
        val mail: String = binding.loginEmailInputField.text.toString()
        val password: String = binding.loginPasswordInputField.text.toString()

        authentication.signInWithEmailAndPassword(mail,password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(this.context, "Logged User", Toast.LENGTH_SHORT).show()
                val profileFragment = ProfileFragment()
                val transaction =  fragmentManager?.beginTransaction()
                transaction?.replace(R.id.ic_wrapper, profileFragment)?.commit()
            } else {
                Toast.makeText(this.context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUser(binding: FragmentLoginBinding) {
        val email: String = binding.loginEmailInputField.text.toString()
        val password: String = binding.loginPasswordInputField.text.toString()
        val root: View = binding.root

        authentication.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this.context, "Registered User", Toast.LENGTH_SHORT).show()
                val profileFragment = ProfileFragment()
                val transaction =  fragmentManager?.beginTransaction()
                transaction?.replace(R.id.ic_wrapper, profileFragment)?.commit()
            } else {
                Toast.makeText(this.context, "Error while registered user", Toast.LENGTH_SHORT).show()
            }
        }
    }

}