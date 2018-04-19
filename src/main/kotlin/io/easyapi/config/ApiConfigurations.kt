package io.easyapi.config

import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


/**
 * Habilita CORS para todos
 * Ejemplo de uso: "@Bean fun corsConfigurer() = enableCorsForAll()"
 */
fun enableCorsForAll(): WebMvcConfigurer {
    return object : WebMvcConfigurer {
        override fun addCorsMappings(registry: CorsRegistry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("*")
        }
    }
}

