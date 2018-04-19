package io.easyapi.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * 400 - Error en el request.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException(msg: String?) : RuntimeException(msg)

/**
 * 500 - Error de sistema
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class SystemException(msg: String?) : RuntimeException(msg)

/**
 * 401 - Error de acceso no autorizado.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
class AccessException(msg: String?) : RuntimeException(msg)
