package com.kelvinsugiarto.ministockwatch.ui.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.navigation.fragment.findNavController
import com.kelvinsugiarto.ministockwatch.ui.R
import com.kelvinsugiarto.ministockwatch.ui.ui.main.MainActivity
import com.kelvinsugiarto.ministockwatch.ui.utils.setGone
import com.kelvinsugiarto.ministockwatch.ui.utils.setVisible
import com.kelvinsugiarto.ministockwatch.ui.utils.showSuccessSnackbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel by viewModel<LoginViewModel>()
    lateinit var loginView: View
    lateinit var loginButton:Button
    lateinit var editTextEmail: EditText
    lateinit var progressBarLoading:ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginView = inflater.inflate(R.layout.login_fragment, container, false)
        loginButton = loginView.findViewById(R.id.button_login)
        editTextEmail = loginView.findViewById(R.id.edit_text_email)

        progressBarLoading = loginView.findViewById(R.id.progress_bar_loading)
        loginButton.setOnClickListener {
            progressBarLoading.setVisible()
            viewModel.doLogin(editTextEmail.text.toString())
        }

        return loginView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.loginStateObservable.observe(viewLifecycleOwner) {
            when (it) {
                true->{
                    navigateToWatchList()
                    (activity as MainActivity).isLoggedIn = true
                    (activity as MainActivity).invalidateOptionsMenu()
                }
                false->{
                    (activity as MainActivity).isLoggedIn = false
                    (activity as MainActivity).invalidateOptionsMenu()
                }
            }
        }


        viewModel.loginObservable.observe(viewLifecycleOwner) {
            when (it) {
                LoginResult.Success("Success") -> {
                    val view = requireActivity().findViewById(android.R.id.content) as View
                    view.showSuccessSnackbar("Login Success")

                    navigateToWatchList()

                }
                LoginResult.Error("error") -> {
                    progressBarLoading.setGone()
                }
                else -> LoginResult.Error("error")
            }
        }



    }

    private fun navigateToWatchList() {
        val action =
            LoginFragmentDirections.actionLoginFragmentToWatchListFragment()
        if (findNavController().currentDestination?.id == R.id.loginFragment) {
                findNavController().navigate(action)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        (activity as MainActivity).hideBottomNavigation()
        (activity as MainActivity).setToolbarTitle("Masuk")
    }

}