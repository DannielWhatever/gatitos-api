package cl.gatitos.api

import cl.gatitos.App
import cl.gatitos.domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*


@ExtendWith(SpringExtension::class)
@SpringBootTest(
        classes = arrayOf(
                App::class
        ),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate


    @Nested
    inner class AdoptionsApi {

        @Nested
        inner class GetCats{

            @Test
            fun whenCalled_shouldReturnOk() {
                val result = testRestTemplate
                        .getForEntity("/adoptions", List::class.java)

                assertThat(result).isNotNull
                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
                val finalList = result.body as List<LinkedHashMap<String,String>>
                assertThat(finalList[0].get("name")).isEqualTo(exampleCat().name)
            }
        }

        @Nested
        inner class GetCatsById{

            @Test
            fun givenAValidId_shouldReturnOk() {
                val result = testRestTemplate
                        .getForEntity("/adoptions/@validId", CatForAdoption::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }

            @Test
            fun givenAnInvalidId_shouldReturn404() {
                val result = testRestTemplate
                        .getForEntity("/adoptions/invalidId", CatForAdoption::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
            }

        }

        @Nested
        inner class GetCatsByAuthor{

            @Test
            fun whenCalled_shouldReturnOk() {
                val result = testRestTemplate
                        .getForEntity("/adoptions/author/@validId", List::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }

        }

        @Nested
        inner class PostCat{

            @Test
            fun whenCalled_shouldReturnOk() {
                val result = testRestTemplate
                        .postForEntity("/adoptions", HttpEntity(exampleNewCat()), CatForAdoption::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }

        }


        @Nested
        inner class DeleteCat{
            @Test
            fun whenCalled_shouldReturnOk() {
                val result = testRestTemplate
                        .exchange<String>("/adoptions/idOk", HttpMethod.DELETE)
                assertThat(result.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
            }
        }



    }

    @Nested
    inner class CommonsApi {

        @Nested
        inner class GetCities {


            @Test
            fun whenCalled_shouldReturnOk() {
                val result = testRestTemplate
                        .getForEntity("/commons/cities", List::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }

        }

    }


    @Nested
    inner class LoginApi {

        @Nested
        inner class PostLogin {

            @Test
            fun givenANewUser_shouldCreateThisOk() {
                val httpEntity = null
                val result = testRestTemplate
                        .postForEntity( "/login", httpEntity, User::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }



        }

    }


    @Nested
    inner class UsersApi {

        @Nested
        inner class GetUserById {

            @Test
            fun givenAValidUserId_shouldReturnOk() {
                val result = testRestTemplate
                        .getForEntity("/users/@validUser", User::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.OK)
            }

            @Test
            fun givenAnInvalidUserId_shouldReturn404() {
                val result = testRestTemplate
                        .getForEntity("/users/kasddkjs", User::class.java)

                assertThat(result?.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
            }


        }

    }

}