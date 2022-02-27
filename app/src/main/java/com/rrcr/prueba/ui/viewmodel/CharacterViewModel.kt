package com.rrcr.prueba.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrcr.prueba.data.Repository
import com.rrcr.prueba.data.model.MainData
import com.rrcr.prueba.data.model.toCharacterData
import com.rrcr.prueba.data.model.toDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val dataModelList = MutableLiveData<List<MainData>>()
    val isLoading = MutableLiveData<Boolean>()
    var resultados = mutableListOf<MainData>()
    var characterSelected = MutableLiveData<MainData?>()

    fun getData() {
        viewModelScope.launch {
            isLoading.postValue(true)

            resultados.addAll( repository.getDataFromApi(dataModelList.value?.size.toString()))
            if (!resultados.isNullOrEmpty()) {
                dataModelList.postValue(resultados)
                isLoading.postValue(false)
            }
        }
    }

    fun setCharacterSelected(character: MainData?){
        if(character != null){
            characterSelected.postValue(character!!)
        }else{
            characterSelected.postValue(null)
        }
    }

    fun addToFav(character: MainData){
        viewModelScope.launch {
            Log.d("****Favorito", "añadiendo a fav $character")
            repository.insertFav(character.toCharacterData())
        }
    }



}