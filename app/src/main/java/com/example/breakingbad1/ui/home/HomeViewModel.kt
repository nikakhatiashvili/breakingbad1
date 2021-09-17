package com.example.breakingbad1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad1.model.Characters
import com.example.breakingbad1.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var _characters = MutableLiveData<List<Characters>> ()
    val characters: LiveData<List<Characters>> get() = _characters


    fun load(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                ApiService.breakService.getAllCharacters()
            }
            _characters.postValue(data.body())
        }
    }
}