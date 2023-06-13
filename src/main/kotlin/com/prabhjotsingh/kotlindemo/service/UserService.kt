package com.prabhjotsingh.kotlindemo.service

import com.prabhjotsingh.kotlindemo.model.AppUser
import com.prabhjotsingh.kotlindemo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<AppUser> {
        return userRepository.findAll()
    }

    fun getUserById(id: Int): AppUser? {
        return userRepository.findById(id).orElse(null)
    }

    fun createUser(user: AppUser): AppUser {
        val existingUsers = getUsers()
        val nextId = existingUsers.maxByOrNull { it.id ?: 0 }?.id?.plus(1) ?: 1
        user.id = nextId
        return userRepository.save(user)
    }

    fun login(email: String?, username: String?, password: String): Boolean {
        if (email.isNullOrEmpty() && username.isNullOrEmpty()) {
            return false
        }
        val user = if (!email.isNullOrEmpty()) {
            userRepository.findByEmail(email)
        } else {
            userRepository.findByUsername(username!!)
        }
        if (user == null || user.password != password) {
            return false
        }
        user.isLoggedIn = true
        userRepository.save(user)
        return true
    }

    fun logout(email: String): Boolean {
        val user = userRepository.findByEmail(email) ?: return false
        user.isLoggedIn = false
        userRepository.save(user)
        return true
    }

    fun updateUser(id: Int, user: AppUser): AppUser? {
        val existingUser = userRepository.findById(id).orElse(null)
        if (existingUser != null) {
            existingUser.firstName = user.firstName
            existingUser.lastName = user.lastName
            existingUser.password = user.password
            existingUser.isLoggedIn = user.isLoggedIn
            return userRepository.save(existingUser)
        }
        return null
    }

    fun deleteUser(id: Int): Boolean {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
            return true
        }
        return false
    }
}
