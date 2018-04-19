package cl.gatitos.userss

import cl.gatitos.domain.User
import cl.gatitos.domain.UsersService
import io.easyapi.response.ifExist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
class UsersRest {

    @Autowired lateinit var usersService: UsersService
    @Autowired lateinit var res: HttpServletResponse


    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: String): User? {
        return ifExist(usersService.getUserById(id), res)
    }

    @PostMapping("/login/google")
    fun postLoginGoogle(@RequestBody userSignedIn: User): User? {
        return usersService.loginGoogle(userSignedIn)
    }



}
