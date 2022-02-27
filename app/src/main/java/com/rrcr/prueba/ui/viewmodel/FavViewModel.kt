package com.rrcr.prueba.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrcr.prueba.data.Repository
import com.rrcr.prueba.data.model.CharacterData
import com.rrcr.prueba.data.model.MainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val dataModelList = MutableLiveData<List<CharacterData>>()
    val isLoading = MutableLiveData<Boolean>()
    var resultados = mutableListOf<CharacterData>()
    var characterSelected = MutableLiveData<CharacterData?>()
    val noData = MutableLiveData<Boolean>()

    fun getData() {
        viewModelScope.launch {
            isLoading.postValue(true)
            resultados.addAll( repository.getAllFavorites())

            if (!resultados.isNullOrEmpty()) {
                dataModelList.postValue(resultados)

            }else{
                noData.postValue(true)
            }
            isLoading.postValue(false)
        }
    }

    fun setCharacterSelected(character: CharacterData?){
        if(character != null){
            characterSelected.postValue(character!!)
        }else{
            characterSelected.postValue(null)
        }
    }
}