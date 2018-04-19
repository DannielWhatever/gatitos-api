package cl.gatitos.api

import cl.gatitos.domain.AdoptionsService
import cl.gatitos.domain.CatForAdoption
import io.easyapi.response.ifExist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/adoptions")
class AdoptionsRest {

    @Autowired lateinit var adoptionService: AdoptionsService
    @Autowired lateinit var res: HttpServletResponse

    @GetMapping
    fun getCats(): List<CatForAdoption> {
        return this.adoptionService.getCats()
    }

    @GetMapping("/{id}")
    fun getCatById(@PathVariable id: String): CatForAdoption? {
        return ifExist(this.adoptionService.getCatById(id), res)
    }

    @GetMapping("/author/{idAuthor}")
    fun getCatsByAuthor(@PathVariable idAuthor: String): List<CatForAdoption> {
        return this.adoptionService.getCatsByAuthor(idAuthor)
    }

    @PostMapping
    fun postCat(@RequestBody newCat: CatForAdoption): CatForAdoption? {
        return this.adoptionService.saveCat(newCat)
    }

    @DeleteMapping("/{id}")
    fun deleteCatById(@PathVariable id: String): ResponseEntity<Any> {
        this.adoptionService.deleteCatById(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}
