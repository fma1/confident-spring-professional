package com.marcobehler.myfancypdfinvoices.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.myfancypdfinvoices.ApplicationLauncher
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource, Scope}
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
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

  // Needed for @RequestParam validation
  @Bean
  def methodValidationPostProcessor = new MethodValidationPostProcessor()
}
