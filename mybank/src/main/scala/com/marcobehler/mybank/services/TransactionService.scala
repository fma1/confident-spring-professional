package com.marcobehler.mybank.services

import com.marcobehler.mybank.model.Transaction

import java.time.{Instant, ZonedDateTime}
import java.util.UUID
import scala.collection.concurrent.TrieMap

class TransactionService {
  val transactionMap = new TrieMap[String, Transaction]()

  def create(amount: BigDecimal, reference: String): Transaction = {
    val id = UUID.randomUUID().toString
    val transaction = Transaction(id, amount, ZonedDateTime.now(), reference)
    transactionMap.put(id, transaction)
    transaction
  }

  def findById(id: String): Transaction = {
    transactionMap(id)
  }

  def findAll(): Iterable[Transaction] = {
    transactionMap.values
  }
}
