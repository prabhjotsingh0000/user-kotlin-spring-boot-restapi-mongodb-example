package com.prabhjotsingh.kotlindemo.repository

import com.prabhjotsingh.kotlindemo.model.AppUser
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserRepository : MongoRepository<AppUser, Int> {
  fun findByEmail(email: String): AppUser?
  fun findByUsername(username: String): AppUser?
}