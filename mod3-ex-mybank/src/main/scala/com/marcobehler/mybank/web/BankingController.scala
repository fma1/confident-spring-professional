package com.marcobehler.mybank.web

import com.marcobehler.mybank.dto.TransactionDTO
import com.marcobehler.mybank.model.Transaction
import com.marcobehler.mybank.services.TransactionService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import scala.jdk.CollectionConverters._

@RestController
class BankingController(transactionService: TransactionService) {

  @GetMapping(value = Array("/transactions"), produces = Array(MediaType.APPLICATION_XML_VALUE))
  def transactions: JList[Transaction] = {
    transactionService.findAll().toList.asJava
  }

  @PostMapping(value = Array("/transactions"), produces = Array(MediaType.APPLICATION_XML_VALUE))
  def createTransaction(@RequestBody transactionDTO: TransactionDTO): Transaction = {
    transactionService.create(transactionDTO.amount, transactionDTO.reference)
  }
}
