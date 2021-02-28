package com.example.androiddevchallenge.model

data class Pet(
    val name: String,
    val species: Species,
    val personality: Personality,
    val sex: Sex,
    val hobbies: String = ""
)

enum class Species {
    DOG,
    CAT,
    SQUIRREL,
    RABBIT
}

enum class Personality {
    CRANKY,
    PEPPY,
    SISTERLY,
    LAZY,
    NORMAL,
    SNOOTY,
    JOCK
}

enum class Sex {
    MALE,
    FEMALE
}
