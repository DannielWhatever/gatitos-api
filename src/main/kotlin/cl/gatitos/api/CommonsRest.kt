package cl.gatitos.userss

import cl.gatitos.domain.CommonsService
import io.easyapi.response.SimpleValue
import io.easyapi.response.sendImage
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/commons")
class CommonsRest {

    @Autowired lateinit var res: HttpServletResponse
    @Autowired lateinit var commonsService: CommonsService

    @GetMapping("/cities")
    fun getCities(): List<String> {
        return cl.gatitos.domain.cities
    }

    @PostMapping("/images")
    fun uploadImage(@RequestParam("file") file: MultipartFile): SimpleValue<String> {
        //logger.debug { file }
        val pictureId = commonsService.uploadImage(Base64.getEncoder().encode(file.bytes))
        return SimpleValue(pictureId)
    }

    @GetMapping("/images/{id}/64")
    fun getImageBase64(@PathVariable id: String): ResponseEntity<String> {
        val media = commonsService.getImage(id)
        return ResponseEntity.ok(media)
    }

    @ResponseBody
    @GetMapping("/images/{id}")
    fun getImage(@PathVariable id: String) {
        val base64image = commonsService.getImage(id)
        sendImage(base64image, res)
    }


    companion object: KLogging()

}
