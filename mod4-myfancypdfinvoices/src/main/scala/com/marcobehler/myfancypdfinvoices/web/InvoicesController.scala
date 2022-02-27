package com.marcobehler.myfancypdfinvoices.web
import com.marcobehler.myfancypdfinvoices.dto.InvoiceDTO
import com.marcobehler.myfancypdfinvoices.model.Invoice
import com.marcobehler.myfancypdfinvoices.services.InvoiceService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, PostMapping, RequestBody, RequestParam, ResponseBody, RestController}

import java.util.{List => JList}
import javax.validation.Valid
import javax.validation.constraints.{Max, Min, NotBlank}

@RestController
@Validated
class InvoicesController(invoicesService: InvoiceService) {

  @GetMapping(Array("/invoices"))
  def invoices(): JList[Invoice] = {
    invoicesService.findAll()
  }

  @PostMapping(Array("/invoices"))
  def createInvoiceRequestBody(@RequestBody @Valid invoiceDTO: InvoiceDTO): Invoice = {
    invoicesService.create(invoiceDTO.userId, invoiceDTO.amount)
  }

  @PostMapping(Array("/invoicesRP"))
  def createInvoiceRequestParam(@RequestParam("user_id") @NotBlank userId: String, @RequestParam @Min(10) @Max(50) amount: Int): Invoice = {
    invoicesService.create(userId, amount)
  }

  @PostMapping(Array("/invoices/{userId}/{amount}"))
  def createInvoicePathVariable(@PathVariable userId: String, @PathVariable amount: Int): Invoice = {
    invoicesService.create(userId, amount)
  }
}
