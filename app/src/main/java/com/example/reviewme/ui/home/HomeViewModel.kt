package com.example.reviewme.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val locations: MutableLiveData<List<String>> = MutableLiveData<List<String>>().apply {
        value = listOf("A", "B", "C", "D", "E")
    }

}