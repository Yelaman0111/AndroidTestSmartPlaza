package com.bignerdranch.android.smartplazatest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.smartplazatest.model.ProductList
import com.bignerdranch.android.smartplazatest.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val getProductResponse: MutableLiveData<Response<ProductList>> = MutableLiveData()
    val getNextProductResponse: MutableLiveData<Response<ProductList>> = MutableLiveData()

    fun getProduct(search: String,
                   highCost: Int?,
                   sortName: String?,
                   lowCost: Int?,
                   pageSize: Int,
                   pageNumber: Int){
        viewModelScope.launch {
            val response: Response<ProductList> = repository.getProduct(search,highCost,sortName,lowCost,pageSize,pageNumber)
            getProductResponse.value = response
        }
    }

    fun getNextPage(search: String,
                   highCost: Int?,
                   sortName: String?,
                   lowCost: Int?,
                   pageSize: Int,
                   pageNumber: Int){
        viewModelScope.launch {
            val response: Response<ProductList> = repository.getProduct(search,highCost,sortName,lowCost,pageSize,pageNumber)
            getNextProductResponse.value = response
        }
    }

}

