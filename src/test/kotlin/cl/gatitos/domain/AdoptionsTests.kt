package cl.gatitos.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.*


internal class AdoptionsTest {

    @Test
    fun shouldCreateCorrectlyACat() {
        val catForAdoption = exampleCat()
        assertThat(catForAdoption).isNotNull
        assertThat(catForAdoption.name).isEqualTo("Samus Snow")
    }

    @Test
    fun givenAnInvalidGenre_shouldThrowAnException() {
        assertThrows(RuntimeException::class.java, {
            CatForAdoption(
                    "@123",
                    "Samus Snow",
                    "macho",
                    3,
                    "https://images.pexels.com/photos/127028/pexels-photo-127028.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                    "soy un gatito muy kawai",
                    exampleAuthor(),
                    null
            )

        })

    }

    @Test
    fun shouldCreateCorrectlyAnAuthor() {
        val authorPub = exampleAuthor()
        assertThat(authorPub).isNotNull
        assertThat(authorPub.name).isNotBlank()
    }

}





// FIXTURES

fun exampleAuthor(): AuthorPub {
    return AuthorPub(
            "@validUser",
            "Rosa Espinoza",
            "Santiago",
            "rosa@espinoza.tti",
            "+569-2222-2222"
    )
}


fun exampleCat() : CatForAdoption {
    return CatForAdoption(
            "@validId",
            "Samus Snow",
            "Gatito",
            3,
            "https://images.pexels.com/photos/127028/pexels-photo-127028.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "soy un gatito muy kawai",
            exampleAuthor(),
            Date()
    )
}


fun exampleNewCat() : CatForAdoption {
    return CatForAdoption(
            null,
            "Samus Snow",
            "Gatito",
            3,
            "https://images.pexels.com/photos/127028/pexels-photo-127028.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
            "soy un gatito muy kawai",
            exampleAuthor(),
            Date()
    )
}



