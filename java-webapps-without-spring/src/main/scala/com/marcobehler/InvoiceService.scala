package com.marcobehler

import java.util.concurrent.CopyOnWriteArrayList

class InvoiceService {
  private val invoices = new CopyOnWriteArrayList[Invoice]()

  def findAll(): CopyOnWriteArrayList[Invoice] = {
    invoices
  }

  def create(userId: String, amount: Int): Invoice = {
    // TODO: real pdf creation and storing it on network server
    val invoice = Invoice(userId = userId, amount = amount, pdfUrl = "http://www.africau.edu/images/default/sample.pdf")
    invoices.add(invoice)
    invoice
  }
}
