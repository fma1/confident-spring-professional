package com.marcobehler.myfancypdfinvoices.services

import com.marcobehler.myfancypdfinvoices.model.User
import org.springframework.stereotype.Component

import java.util.UUID

@Component
class UserService {
  def findById(id: String): User = {
    User(id, UUID.randomUUID().toString)
  }
}
