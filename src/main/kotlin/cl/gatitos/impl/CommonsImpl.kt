package cl.gatitos.impl

import cl.gatitos.domain.CommonsService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.TypeAlias
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service


/**
 * DTOs
 */
@TypeAlias("image")
data class ImageDto(
        @Id val id: String?,
        val data: String?
)

/**
 * Repositories
 */
interface ImageRepository : CrudRepository<ImageDto, String>





/**
 * Spring Service
 */
@Service
class CommonsServiceImpl : CommonsService {
    override fun uploadImage(bytes: ByteArray): String {
        val imageDto = ImageDto(null, String(bytes))
        val imageSaved = imageRepository.save(imageDto)
        return imageSaved.id!!
    }

    override fun getImage(id: String): String {
        val imageDto = imageRepository.findById(id)
        //val sb = StringBuilder()
        //sb.append("data:image/png;base64,")
        //sb.append(checkNotNull(imageDto.get().data))
        return checkNotNull(imageDto.get().data)
    }

    @Autowired
    lateinit var imageRepository: ImageRepository



    /**
     * static area
     */
    companion object: KLogging()


}
