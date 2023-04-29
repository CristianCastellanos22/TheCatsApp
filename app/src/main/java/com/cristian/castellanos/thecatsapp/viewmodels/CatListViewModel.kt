package com.cristian.castellanos.thecatsapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cristian.castellanos.thecatsapp.data.ApiResponseStatus
import com.cristian.castellanos.thecatsapp.data.CatRepository
import com.cristian.castellanos.thecatsapp.models.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val catRepository: CatRepository,
) : ViewModel() {

    private val _status = MutableLiveData<ApiResponseStatus<Any>>()
    val status: LiveData<ApiResponseStatus<Any>> get() = _status

    private val _catList = MutableLiveData<List<Cat>>()
    val catList: LiveData<List<Cat>> get() = _catList

    init {
        getCatList()
    }

    private fun getCatList() {
        viewModelScope.launch {
            _status.value = ApiResponseStatus.Loading()
            catRepository.getCatsCollection().collect {
                handleResponseStatus(it)
            }
        }
    }

    private fun handleResponseStatus(apiResponseStatus: ApiResponseStatus<List<Cat>>) {
        if (apiResponseStatus is ApiResponseStatus.Success) {
            _catList.value = apiResponseStatus.data
        }
        _status.value = apiResponseStatus as ApiResponseStatus<Any>
    }

}
