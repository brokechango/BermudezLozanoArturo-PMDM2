package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Serializable
data class Product(
    @Json(name = "_id")val id: String,
    val name: String,
    val description: String,
    val price: String,
    val category: String,
    val image: String,
    val active: Boolean,
)