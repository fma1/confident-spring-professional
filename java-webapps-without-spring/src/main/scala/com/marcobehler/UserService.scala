package com.marcobehler

import java.util.UUID

class UserService {
  def findById(id: String): User = {
    User(id, UUID.randomUUID().toString)
  }
}
