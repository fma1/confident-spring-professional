package com.marcobehler.mybank.services

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Service
@Profile(Array("dev"))
class DummyTransactionServiceLoader(transactionService: TransactionService) {

  @PostConstruct
  def setup(): Unit = {
    println("Creating dev invoices...")
    transactionService.create(50, "Ref1")
    transactionService.create(50, "Ref2")
  }
}
