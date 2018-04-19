/**
 * module setup
 */
package cl.gatitos

import cl.gatitos.impl.CatForAdoptionDto
import cl.gatitos.impl.CatForAdoptionRepository
import cl.gatitos.impl.UserDto
import cl.gatitos.impl.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct

@Service
class AppSetup {

    @Autowired
    lateinit var catForAdoptionRepository: CatForAdoptionRepository
    @Autowired
    lateinit var userRepository: UsersRepository


    @PostConstruct
    fun onInit() {
        //clean all?
        if (true) {
            this.catForAdoptionRepository.deleteAll();
            this.userRepository.deleteAll();
        }

        if (this.catForAdoptionRepository.findAll().toList().isEmpty()){
            firstLoad()
        }
    }

    fun firstLoad() {


        val user1 = UserDto(
                "@validUser",
                "123123",
                "Rosa Espinoza",
                "",
                "Santiago",
                "rosa@espinoza.tti",
                "+569-2222-2222"
        )
        userRepository.save(user1)


        val cat1 = CatForAdoptionDto(
                "@validId",
                "Samus Snow",
                "Gatito",
                3,
                "https://images.pexels.com/photos/127028/pexels-photo-127028.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "soy un gatito muy kawai",
                "@validUser",
                Date()
        )
        this.catForAdoptionRepository.save(cat1)
    }

}

data class IDontLikeClassesIcon(val wtf: StackOverflowError)