package cl.gatitos.domain

import java.util.*


data class AuthorPub(
        val id: String?,
        val name: String?,
        val city: String?,
        val email: String?,
        val phone: String?
)

class CatForAdoption(
        val id: String?,
        val name: String,
        genre: String,
        val age: Int,
        val picture: String,
        val description: String?,
        val author: AuthorPub,
        publishedDate: Date?
){
    val genre = if (genre in catGenres) genre else throw RuntimeException("invalid genre")
    val publishedDate = if (publishedDate != null) publishedDate else Date()
}


interface AdoptionsService {
    fun getCats(): List<CatForAdoption>
    fun getCatById(id: String): CatForAdoption?
    fun saveCat(newCat: CatForAdoption): CatForAdoption?

    fun getCatsByAuthor(idAuthor: String): List<CatForAdoption>
    fun deleteCatById(id: String)
}

