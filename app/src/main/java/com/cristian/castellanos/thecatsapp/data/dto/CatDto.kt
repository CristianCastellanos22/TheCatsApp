package com.cristian.castellanos.thecatsapp.data.dto

import com.cristian.castellanos.thecatsapp.models.Cat
import com.google.gson.annotations.SerializedName

private const val URL = "https://cdn2.thecatapi.com/images/"
private const val FINAL_URL = ".jpg"

data class CatDto(
    @SerializedName("name")
    val breedName: String,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("affection_level")
    val affectionLevel: Int,
    @SerializedName("intelligence")
    val intelligence: Int,
    @SerializedName("reference_image_id")
    val imageUrl: String?,
)

fun CatDto.mapToDomain(): Cat {
    return with(this) {
        Cat(
            breedName = breedName,
            origin = origin,
            affectionLevel = affectionLevel,
            intelligence = intelligence,
            imageUrl = URL.plus(imageUrl.orEmpty()).plus(FINAL_URL)
        )
    }
}

