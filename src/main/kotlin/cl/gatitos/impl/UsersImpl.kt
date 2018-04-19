package cl.gatitos.impl

import cl.gatitos.domain.User
import cl.gatitos.domain.UsersService
import cl.gatitos.domain.defaultCity
import io.easyapi.exceptions.AccessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service

/**
 * DTOs
 */
@TypeAlias("user")
data class UserDto(
        @Id val id: String?,
        val idGoogle: String?,
        val name: String,
        val avatar: String?,
        val city: String?,
        val email: String,
        val phone: String?
)

/**
 * Repositories
 */
interface UsersRepository : CrudRepository<UserDto, String>{
    fun findByIdGoogle(idGoogle: String): List<UserDto>
}





/**
 * Spring Service
 */
@Service
class UsersServiceImpl : UsersService {
    override fun getUsers(): List<User> {
        return usersRepository.findAll().map { buildUser(it) }
    }

    override fun getUserById(id: String): User? {
        val userDto = usersRepository.findById(id)
        return if(userDto.isPresent) buildUser(userDto.get()) else null
    }

    override fun loginGoogle(userSignedIn: User): User? {
        val idGoogle = userSignedIn.idGoogle ?: throw AccessException("only google account allowed for now :c")
        val possibleUsers = usersRepository.findByIdGoogle(idGoogle)
        if(possibleUsers.isEmpty()) {
            return this.saveUser(userSignedIn)
        } else {
            return buildUser(possibleUsers[0])
        }
    }

    override fun saveUser(user: User): User? {
        val userDto =  UserDto(
                user.id,
                user.idGoogle,
                user.name,
                user.avatar,
                user.city?: defaultCity,
                user.email,
                user.phone
        )
        val savedUser = usersRepository.save(userDto)
        return buildUser(savedUser)
    }


    @Autowired lateinit var usersRepository: UsersRepository



    /**
     * static area
     */
    companion object {
        fun buildUser(user: UserDto): User {
            return User(
                    user.id,
                    user.idGoogle,
                    user.name,
                    user.avatar,
                    user.city,
                    user.email,
                    user.phone
            )
        }

    }


}
