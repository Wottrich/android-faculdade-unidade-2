package io.github.wottrich.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 17/04/2021
 *
 * Copyright © 2021 AndroidFaculdadeUnidade2. All rights reserved.
 *
 */
 
class MainViewModel : ViewModel() {

    private var lastClickedName: String? = null
    private var internalItems = mutableListOf<String>()

    private val _listItems = MutableLiveData<List<String>>()
    val listItems: LiveData<List<String>> = _listItems

    fun sendName(name: String) {
        internalItems.add(name)
        _listItems.postValue(internalItems)
    }

    fun removeLastClickedName() {
        val newList = internalItems.apply {
            remove(lastClickedName)
        }
        _listItems.postValue(newList)
    }

    fun updateLastClickedName(name: String) {
        this.lastClickedName = name
    }

}