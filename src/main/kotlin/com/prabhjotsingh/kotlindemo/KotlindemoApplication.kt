package com.prabhjotsingh.kotlindemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [JpaRepositoriesAutoConfiguration::class])
class KotlindemoApplication


fun main(args: Array<String>) {
	runApplication<KotlindemoApplication>(*args)
}
