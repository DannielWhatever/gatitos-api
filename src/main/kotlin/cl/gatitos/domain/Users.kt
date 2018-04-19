package cl.gatitos.domain


data class User(
        val id: String?,
        val idGoogle: String?,
        val name: String,
        val avatar: String?,
        val city: String?,
        val email: String,
        val phone: String?
)

interface UsersService {
    fun getUsers(): List<User>
    fun getUserById(id: String): User?
    fun saveUser(user: User): User?

    fun loginGoogle(userSignedIn: User): User?

}

