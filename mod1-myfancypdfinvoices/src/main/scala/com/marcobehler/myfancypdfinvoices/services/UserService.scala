package com.marcobehler.myfancypdfinvoices.services

import com.marcobehler.myfancypdfinvoices.model.User

import java.util.UUID

class UserService {
  def findById(id: String): User = {
    User(id, UUID.randomUUID().toString)
  }
}
