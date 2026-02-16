package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.repository

import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
}