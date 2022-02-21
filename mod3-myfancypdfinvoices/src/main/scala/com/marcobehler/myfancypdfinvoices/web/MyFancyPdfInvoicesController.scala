package com.marcobehler.myfancypdfinvoices.web

import com.marcobehler.myfancypdfinvoices.model.Invoice
import com.marcobehler.myfancypdfinvoices.services.InvoiceService
import org.springframework.web.bind.annotation.{GetMapping, PostMapping, RequestParam, ResponseBody, RestController}

import java.util.{List => JList}

@RestController
class MyFancyPdfInvoicesController(invoicesService: InvoiceService) {

  @GetMapping(Array("/invoices"))
  def invoices(): JList[Invoice] = {
    invoicesService.findAll()
  }

  @PostMapping(Array("/invoices"))
  def createInvoice(@RequestParam("user_id") userId: String, @RequestParam("amount") amount: Int): Invoice = {
    invoicesService.create(userId, amount)
  }
}
