package cl.gatitos.domain

val catGenres = arrayOf("Gatito", "Gatita")
val cities = arrayListOf("Santiago", "Valparaiso")
val defaultCity = "Santiago"

interface CommonsService {

    fun getImage(id: String): String
    fun uploadImage(bytes: ByteArray): String
}



