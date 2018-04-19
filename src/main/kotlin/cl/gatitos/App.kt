package cl.gatitos

import io.easyapi.config.enableCorsForAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

fun main(args: Array<String>) {
    runApplication<App>(*args)
}

@SpringBootApplication
class App{


    @Bean fun corsConfigurer() = enableCorsForAll()

    companion object {
        val logger: Logger =
                LoggerFactory.getLogger(App::class.java)
    }

}

