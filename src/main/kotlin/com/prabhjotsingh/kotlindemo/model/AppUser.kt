package com.prabhjotsingh.kotlindemo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("app-user")
data class AppUser(
  @Id
  val id: UUID = UUID.randomUUID(),
  var firstName: String,
  var lastName: String,
  val email: String,
  val username: String,
  var password: String,
  var isLoggedIn: Boolean = false
)