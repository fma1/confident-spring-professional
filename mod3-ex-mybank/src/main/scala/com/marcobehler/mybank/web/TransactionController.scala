package com.marcobehler.mybank.web

import com.marcobehler.mybank.dto.TransactionDTO
import com.marcobehler.mybank.model.Transaction
import com.marcobehler.mybank.services.TransactionService
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid
import scala.jdk.CollectionConverters._

@RestController
class TransactionController(transactionService: TransactionService) {

  @GetMapping(Array("/transactions"))
  def transactions: JList[Transaction] = {
    transactionService.findAll().toList.asJava
  }

  @PostMapping(Array("/transactions"))
  def createTransaction(@RequestBody @Valid transactionDTO: TransactionDTO): Transaction = {
    transactionService.create(transactionDTO.amount, transactionDTO.reference)
  }
}
