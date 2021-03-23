package com.akdogan.barebonepaging.repository

import androidx.annotation.DrawableRes
import java.util.*

data class FantasyCharacter(
    val id: String = getRandomId(),
    val name: String,
    val sex: Sex,
    val race: String,
    val characterClass: String,
    val age: Int,
    @DrawableRes val thumbnail: Int
) {
    companion object{
        fun create(
            id: String?,
            name: String,
            sex: Sex,
            race: String,
            characterClass: String,
            age: Int,
            @DrawableRes thumbnail: Int
        ): FantasyCharacter {
            return FantasyCharacter(
            id = id ?: getRandomId(),
            name = name,
            sex = sex,
            race = race,
            characterClass = characterClass,
            age = age,
            thumbnail = thumbnail
            )
        }
    }
}

enum class Sex{
    FEMALE, MALE, OTHER;
}

fun getRandomId(): String{
    val id = UUID.randomUUID().toString()
    return id
}




