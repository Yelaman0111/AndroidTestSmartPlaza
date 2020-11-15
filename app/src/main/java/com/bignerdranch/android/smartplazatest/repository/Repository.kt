package com.bignerdranch.android.smartplazatest.repository

import com.bignerdranch.android.smartplazatest.api.RetrofitInstance
import com.bignerdranch.android.smartplazatest.model.ProductList
import retrofit2.Response


class Repository {


    suspend fun getProduct(search: String,
                            highCost: Int?,
                            sortName: String?,
                            lowCost: Int?,
                            pageSize: Int,
                            pageNumber: Int)
            :Response<ProductList> {
        return RetrofitInstance.api.getProduct(search,highCost,sortName,lowCost,pageSize,pageNumber)
    }

    suspend fun getNextProduct(search: String,
                           highCost: Int?,
                           sortName: String?,
                           lowCost: Int?,
                           pageSize: Int,
                           pageNumber: Int)
            :Response<ProductList> {
        return RetrofitInstance.api.getNextProduct(search,highCost,sortName,lowCost,pageSize,pageNumber)
    }
}