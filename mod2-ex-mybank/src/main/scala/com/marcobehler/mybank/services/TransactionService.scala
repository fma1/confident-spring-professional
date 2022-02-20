package com.marcobehler.mybank.services

import com.marcobehler.mybank.model.Transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.time.{Instant, ZonedDateTime}
import java.util.UUID
import scala.collection.concurrent.TrieMap

@Component
class TransactionService(@Value("${bank.slogan}") bankSlogan: String) {
  val transactionMap = new TrieMap[String, Transaction]()

  def create(amount: BigDecimal, reference: String): Transaction = {
    val id = UUID.randomUUID().toString
    val transaction = Transaction(id, amount, ZonedDateTime.now(), reference, bankSlogan)
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
