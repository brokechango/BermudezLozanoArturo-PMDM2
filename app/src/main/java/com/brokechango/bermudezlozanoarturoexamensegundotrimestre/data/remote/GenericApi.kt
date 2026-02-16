package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.remote

import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model.Product
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.remote.response.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenericApi {
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 1
    ): PagedResponse<Product>
}