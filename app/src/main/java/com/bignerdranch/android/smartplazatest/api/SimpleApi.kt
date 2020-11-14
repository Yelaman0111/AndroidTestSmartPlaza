package com.bignerdranch.android.smartplazatest.api

import com.bignerdranch.android.smartplazatest.model.ProductList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {


    @GET("products")
    suspend fun getProduct(@Query("search") search: String,
                           @Query("highCost") highCost: Int,
                           @Query("sortName") sortName: String,
                           @Query("lowCost") lowCost: Int,
                           @Query("pageSize") pageSize: Int,
                           @Query("pageNumber") pageNumber: Int
    ): Response<ProductList>


}