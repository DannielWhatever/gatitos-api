package io.easyapi.response

import org.apache.commons.io.IOUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.io.ByteArrayInputStream
import java.util.*
import javax.servlet.http.HttpServletResponse


/**
 * En caso que el valor exista, retorna el valor.
 * En caso contrario, retorna nulo, y setea el Estado HTTP a 404.
 */
fun <T> ifExist(value: T, res: HttpServletResponse): T?{
    if(value != null) return value
    res.status = 404
    return null
}

/**
 * A partir de una imagen en base64, retorna los bytes.
 * Ademas setea la cabecera content-type.
 */
fun sendImage(base64image: String, res: HttpServletResponse) {
    val bytes = Base64.getMimeDecoder().decode(base64image)
    val inputStream = ByteArrayInputStream(bytes)
    res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
    IOUtils.copy(inputStream, res.outputStream)
}

/**
 * Data class para retornar un solo dato dentro de un json.
 * (sin tener que definir una clase particular para esto)
 */
data class SimpleValue<T>(val data: T)

