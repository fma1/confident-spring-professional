package com.marcobehler.myfancypdfinvoices.services

import com.marcobehler.myfancypdfinvoices.model.Invoice

import java.util.concurrent.CopyOnWriteArrayList

class InvoiceService(userService: UserService) {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  def findAll(): CopyOnWriteArrayList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    Option(userService.findById(userId)) match {
      case Some(_) =>
        // TODO: real pdf creation and storing it on network server
        val invoice = Invoice(userId = userId, amount = amount, pdfUrl = "http://www.africau.edu/images/default/sample.pdf")
        invoices.add(invoice)
        invoice
      case None =>
        throw new IllegalStateException()
    }
  }
}
