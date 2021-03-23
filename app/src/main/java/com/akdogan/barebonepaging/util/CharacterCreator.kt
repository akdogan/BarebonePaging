package com.akdogan.barebonepaging.util

import androidx.annotation.DrawableRes
import com.akdogan.barebonepaging.R
import com.akdogan.barebonepaging.repository.FantasyCharacter
import com.akdogan.barebonepaging.repository.Sex

fun getRandomCharList(amount: Int = 10, debug: Boolean = false): List<FantasyCharacter> {
    val result = mutableListOf<FantasyCharacter>()
    repeat(amount) {
        if (debug) {
            result.add(createRandomChar("--$it--"))
        } else {

            result.add(createRandomChar())
        }
    }
    return result.toList()

}

fun createRandomChar(id: String? = null): FantasyCharacter {
    val myClass = defaultClasses.keys.toList().random()
    val myThumbnail = defaultClasses[myClass]
    return FantasyCharacter.create(
        id = id,
        name = "${defaultFirstNames.random()} ${defaultFirstNames.random()}",
        race = defaultRaces.random(),
        characterClass = myClass,
        sex = getRandomSex(),
        age = getRandomAge(),
        thumbnail = myThumbnail ?: R.drawable.ic_baseline_build_24
    )
}


fun getRandomAge(range: IntRange = 10..60) = range.random()


fun getRandomSex(
    mProb: Int = 48,
    fProb: Int = 48,
    oProb: Int = 4
): Sex {
    if (mProb < 0 || fProb < 0 || oProb < 0) {
        throw IllegalArgumentException("Probability must be positive")
    }

    val result = (1..(mProb + fProb + oProb)).random()
    return when {
        result <= mProb -> Sex.MALE
        result <= mProb + fProb -> Sex.FEMALE
        else -> Sex.OTHER
    }
}


val defaultClasses = hashMapOf<String, @DrawableRes Int>(
    Pair("Barbarian", R.drawable.class_symb_fighter),
    Pair("Bard", R.drawable.class_symb_rogue),
    Pair("Cleric", R.drawable.class_symb_cleric),
    Pair("Druid", R.drawable.class_symb_cleric),
    Pair("Fighter", R.drawable.class_symb_fighter),
    Pair("Monk", R.drawable.class_symb_cleric),
    Pair("Paladin", R.drawable.class_symb_cleric),
    Pair("Ranger", R.drawable.class_symb_ranger),
    Pair("Rogue", R.drawable.class_symb_rogue),
    Pair("Sorcerer", R.drawable.class_symb_wizard),
    Pair("Warlock", R.drawable.class_symb_wizard),
    Pair("Wizard", R.drawable.class_symb_wizard)
)

val defaultRaces = listOf(
    "Dragonborn",
    "Dwarf",
    "Elf",
    "Gnome",
    "Half-Elf",
    "Halfling",
    "Half-Orc",
    "Human",
    "Tiefling"
)

val defaultFirstNames = listOf(
    "Nualtules",
    "Malkuankak",
    "Drumputh",
    "Prumphecnojor",
    "Croltas",
    "Opor",
    "Udohazar",
    "Krivcrath",
    "Ravobarum",
    "Trouxan",
    "Doturim",
    "Goraqull",
    "Brenturim",
    "Tazciar",
    "Lechetemer",
    "Limrinkush",
    "Yrrish",
    "Wrameila",
    "Bilyassa",
    "Drysqwen",
    "Quilsaadi",
    "Perhorn",
    "Sparkbreath",
    "Balyarus",
    "Vahorn",
    "Ianpetor",
    "Zumnan",
)

/*
ravenseeker
 springbeam
 beechheel
 crimsonseeker
Virkian duntis
Nornan florosh
Gentoris eystanos
Adpetor flieltivon
Heibalar toldinthreha
Holadove flowerheel
Faerie greenshine
Holasys rainstar
Jorie fogspell
Nerivyre wildsmile
Lorasatra liphoshendra
Valnala fonascisinn
Shamoira esha
Chaexisys neminaea
Neribanise reivordalkel*/
