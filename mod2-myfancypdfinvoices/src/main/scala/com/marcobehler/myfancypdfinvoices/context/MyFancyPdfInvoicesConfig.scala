package com.marcobehler.myfancypdfinvoices.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.myfancypdfinvoices.ApplicationLauncher
import com.marcobehler.myfancypdfinvoices.services.{InvoiceService, UserService}
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, Scope}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
class MyFancyPdfInvoicesConfig {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}
