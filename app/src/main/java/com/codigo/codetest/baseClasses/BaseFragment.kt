package com.codigo.codetest.baseClasses

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<T : BaseViewModel> : Fragment(), BaseView {

    abstract val viewModel : T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupListeners()
    }

    protected fun showQuickSnackBar(message : String) {
        Snackbar.make(requireView() , message , Snackbar.LENGTH_SHORT).show()
    }

    protected fun showLongSnackBar(message : String) {
        Snackbar.make(requireView() , message , Snackbar.LENGTH_LONG).show()
    }

    protected fun showQuickToastMessage(message : String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLongToastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}