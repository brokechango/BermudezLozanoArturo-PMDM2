package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val image: String,
    val active: Boolean,
)