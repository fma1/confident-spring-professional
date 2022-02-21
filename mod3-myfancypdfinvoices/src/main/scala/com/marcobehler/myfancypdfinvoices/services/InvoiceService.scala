package com.marcobehler.myfancypdfinvoices.services

import com.marcobehler.myfancypdfinvoices.model.Invoice
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import java.util.concurrent.CopyOnWriteArrayList
import javax.annotation.{PostConstruct, PreDestroy}
import scala.annotation.meta.beanSetter

@Component
class InvoiceService(userService: UserService, @Value("${cdn.url}") cdnUrl: String) {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  @PostConstruct
  def init(): Unit = {
    println(cdnUrl)
    println("Printing PDF template from S3...")
    // TODO: Download from S3 and save locally
  }

  // Will only run when shutdown gracefully, which is not the red IntelliJ button
  @PreDestroy
  def shutdown(): Unit = {
    println("Deleting downloaded templates...")
    // TODO: Actual deletion of files
  }

  def findAll(): CopyOnWriteArrayList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    Option(userService.findById(userId)) match {
      case Some(_) =>
        // TODO: real pdf creation and storing it on network server
        val invoice = Invoice(userId = userId, amount = amount, pdfUrl = s"$cdnUrl/images/default/sample.pdf")
        invoices.add(invoice)
        invoice
      case None =>
        throw new IllegalStateException()
    }
  }
}
