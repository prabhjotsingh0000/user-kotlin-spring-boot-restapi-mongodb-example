package com.prabhjotsingh.kotlindemo

import com.prabhjotsingh.kotlindemo.model.AppUser
import com.prabhjotsingh.kotlindemo.service.UserService
import javax.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import java.util.*

@SpringBootApplication(exclude = [JpaRepositoriesAutoConfiguration::class])
@ComponentScan("com.prabhjotsingh.kotlindemo")
class KotlindemoApplication (private val userService: UserService){

	@PostConstruct
	private final fun initializeDummyData() {
		val dataCount = 10000
		val random = Random()

		for (i in 0 until dataCount) {
			val id = random.nextInt(Int.MAX_VALUE)
			val firstName = generateRandomString(8)
			val lastName = generateRandomString(8)
			val email = "${firstName.lowercase()}.${lastName.lowercase()}@example.com"
			val username = "${firstName.lowercase()}${lastName.lowercase()}"
			val password = generateRandomString(10)

			val user = AppUser(
				id = id,
				firstName = firstName,
				lastName = lastName,
				email = email,
				username = username,
				password = password
			)

			val createdUser = userService.createUser(user)
			println("Created user with ID: ${createdUser.id}")
		}
	}

	private fun generateRandomString(length: Int): String {
		val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
		val result = StringBuilder()
		val random = Random()
		for (i in 0 until length) {
			val index = random.nextInt(characters.length)
			result.append(characters[index])
		}
		return result.toString()
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			runApplication<KotlindemoApplication>(*args)
		}
	}

}



