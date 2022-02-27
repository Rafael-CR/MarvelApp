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
class GenericDetailViewModel@Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val dataModelList = MutableLiveData<List<CharacterData>>()
    val isLoading = MutableLiveData<Boolean>()
    val noData = MutableLiveData<Boolean>()
    var resultados = mutableListOf<CharacterData>()


    fun getData(idCharacter: String, details: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            noData.postValue(false)

            resultados.addAll(repository.getDetailsById(dataModelList.value?.size.toString(), idCharacter, details))
            if (!resultados.isNullOrEmpty()) {
                dataModelList.postValue(resultados)

            }else{
                noData.postValue(true)
            }
            isLoading.postValue(false)
        }
    }

}