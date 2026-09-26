package com.example.evidenciadeaprendizaje3.model

data class Station(
    val id: Int,
    val name: String,
    val genre: String,
    val streamUrl: String
)

val sampleStations = listOf(
    Station(1, "IU Digital Radio", "Institucional", "https://ice1.somafm.com/groovesalad-128-mp3"),
    Station(2, "Pop Hits FM", "Pop", "https://ice1.somafm.com/poptron-128-mp3"),
    Station(3, "Jazz Lounge", "Jazz", "https://ice1.somafm.com/sonicuniverse-128-mp3"),
    Station(4, "Rock Classics", "Rock", "https://ice1.somafm.com/bootliquor-128-mp3"),
    Station(5, "Chill Beats", "Lo-fi", "https://ice1.somafm.com/dronezone-128-mp3")
)