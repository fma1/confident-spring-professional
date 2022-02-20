package com.marcobehler.mybank.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.mybank.ApplicationLauncher
import com.marcobehler.mybank.services.TransactionService
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
@PropertySource(value = Array("classpath:/application.properties"))
class MyBankConfig {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())
}
