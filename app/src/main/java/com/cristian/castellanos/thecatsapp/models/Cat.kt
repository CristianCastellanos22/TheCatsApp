package com.cristian.castellanos.thecatsapp.models

data class Cat(
    val breedName: String,
    val origin: String,
    val affectionLevel: Int,
    val intelligence: Int,
    val imageUrl: String?,
)