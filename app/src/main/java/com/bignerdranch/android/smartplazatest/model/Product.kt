package com.bignerdranch.android.smartplazatest.model

import com.google.gson.annotations.SerializedName

data class ProductList (
    @SerializedName("productResponses")  val product: List<Product>
)
data class Product(
    @SerializedName("name")  val name: String,
    @SerializedName("photo_1")  val photo_1: String,
    @SerializedName("price")  val price: Int
)