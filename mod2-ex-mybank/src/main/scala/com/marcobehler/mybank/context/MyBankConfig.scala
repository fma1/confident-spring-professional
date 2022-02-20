package com.marcobehler.mybank.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.mybank.ApplicationLauncher
import com.marcobehler.mybank.services.TransactionService
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
class MyBankConfig {
  @Bean
  def transactionService = new TransactionService()
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())
}
