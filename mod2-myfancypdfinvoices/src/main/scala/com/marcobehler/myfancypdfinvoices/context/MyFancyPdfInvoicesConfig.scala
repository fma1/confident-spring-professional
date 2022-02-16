package com.marcobehler.myfancypdfinvoices.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.myfancypdfinvoices.services.{InvoiceService, UserService}
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class MyFancyPdfInvoicesConfig {
  @Bean
  def userService: UserService = new UserService()
  @Bean
  def invoiceService: InvoiceService = new InvoiceService(userService)
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}
