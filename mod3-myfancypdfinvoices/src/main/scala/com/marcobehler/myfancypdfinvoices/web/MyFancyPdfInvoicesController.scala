package com.marcobehler.myfancypdfinvoices.web

import com.marcobehler.myfancypdfinvoices.dto.InvoiceDTO
import com.marcobehler.myfancypdfinvoices.model.Invoice
import com.marcobehler.myfancypdfinvoices.services.InvoiceService
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestParam, ResponseBody, RestController}

import java.util.{List => JList}

@RestController
class MyFancyPdfInvoicesController(invoicesService: InvoiceService) {

  @GetMapping(Array("/invoices"))
  def invoices(): JList[Invoice] = {
    invoicesService.findAll()
  }

  @PostMapping(Array("/invoices"))
  def createInvoice(@RequestBody invoiceDTO: InvoiceDTO): Invoice = {
    invoicesService.create(invoiceDTO.userId, invoiceDTO.amount)
  }

  /*
  @PostMapping(Array("/invoices/{userId}/{amount}"))
  def createInvoice(@PathVariable userId: String, @PathVariable amount: Int): Invoice = {
    invoicesService.create(userId, amount)
  }
   */
}
