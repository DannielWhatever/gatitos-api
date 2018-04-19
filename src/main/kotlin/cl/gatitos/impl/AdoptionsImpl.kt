package cl.gatitos.impl

import cl.gatitos.domain.AdoptionsService
import cl.gatitos.domain.AuthorPub
import cl.gatitos.domain.CatForAdoption
import io.easyapi.exceptions.BadRequestException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*

/**
 * DTOs
 */

@TypeAlias("catForAdoption")
data class CatForAdoptionDto(
        @Id val id: String?,
        val name: String,
        val genre: String,
        val age: Int,
        val picture: String,
        val description: String?,
        val author: String?,
        val publishedDate: Date
)



/**
 * Repositories
 */
interface CatForAdoptionRepository : CrudRepository<CatForAdoptionDto, String>{
    fun findByAuthor(idAuthor: String): List<CatForAdoptionDto>
}


/**
 * Spring Service
 */
@Service
class AdoptionsServiceImpl : AdoptionsService {

    @Autowired lateinit var catForAdoptionRepository: CatForAdoptionRepository
    @Autowired lateinit var userRepository: UsersRepository


    override fun getCats(): List<CatForAdoption> {
        return this.catForAdoptionRepository.findAll().map {
            buildCat(it, userRepository.findById(checkNotNull(it.author)))
        }
    }

    override fun getCatById(id: String): CatForAdoption? {
        val cat = catForAdoptionRepository.findById(id)
        if(cat.isPresent){
            val author = userRepository.findById(checkNotNull(cat.get().author))
            return buildCat(cat.get(), author)
        }
        return null
    }

    override fun saveCat(newCat: CatForAdoption): CatForAdoption? {
        val catDto = CatForAdoptionDto(
                null,
                newCat.name,
                newCat.genre,
                newCat.age,
                newCat.picture,
                newCat.description,
                newCat.author.id,
                Date()
        )
        val author = userRepository.findById(checkNotNull(catDto.author))
        if(!author.isPresent){
            throw BadRequestException("not found author")
        }
        val catSaved = catForAdoptionRepository.save(catDto)
        return buildCat(catSaved, author)
    }

    override fun getCatsByAuthor(idAuthor: String): List<CatForAdoption> {
        return this.catForAdoptionRepository.findByAuthor(idAuthor).map {
            buildCat(it, userRepository.findById(checkNotNull(it.author)))
        }
    }


    override fun deleteCatById(id: String) {
        catForAdoptionRepository.deleteById(id)
        return
    }


    /**
     * static area
     */
    companion object {
        fun buildCat(cat: CatForAdoptionDto, user: Optional<UserDto>) : CatForAdoption {
            return CatForAdoption(
                    cat.id,
                    cat.name,
                    cat.genre,
                    cat.age,
                    cat.picture,
                    cat.description,
                    buildAuthor(user.get()),
                    cat.publishedDate
            )
        }

        fun buildAuthor(userDto: UserDto): AuthorPub {
            return AuthorPub(
                    userDto.id,
                    userDto.name,
                    userDto.city,
                    userDto.email,
                    userDto.phone
            )
        }
    }


}
