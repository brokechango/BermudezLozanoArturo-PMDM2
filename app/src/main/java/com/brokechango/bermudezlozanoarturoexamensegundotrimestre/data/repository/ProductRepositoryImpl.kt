package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.repository

import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model.Product
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.remote.RetrofitNetwork

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        val response = RetrofitNetwork.api.getProducts()
        return response.results
    }
}