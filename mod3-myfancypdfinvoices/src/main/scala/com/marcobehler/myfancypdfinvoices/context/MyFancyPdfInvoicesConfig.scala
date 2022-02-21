package com.marcobehler.myfancypdfinvoices.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.myfancypdfinvoices.ApplicationLauncher
import com.marcobehler.myfancypdfinvoices.services.{InvoiceService, UserService}
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource, Scope}
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
@PropertySource(Array("classpath:/application.properties"))
@PropertySource(value = Array("classpath:/application-${spring.profiles.active}.properties")
  , ignoreResourceNotFound = true)
@EnableWebMvc
class MyFancyPdfInvoicesConfig {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)
}
