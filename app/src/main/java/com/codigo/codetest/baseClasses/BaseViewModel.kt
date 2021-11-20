package com.codigo.codetest.baseClasses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val toastMessage : MutableLiveData<String> = MutableLiveData()
    var isFragmentFinished : Boolean = false

}