package com.marcobehler.mybank.context

import com.fasterxml.jackson.databind.{ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.mybank.ApplicationLauncher
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource}
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter
import org.springframework.web.servlet.config.annotation.{ContentNegotiationConfigurer, EnableWebMvc, WebMvcConfigurer}

import java.util.{List => JList}

/*
 * EnableWebMVC ensures Spring Web MVC gets loaded with a sane default configuration
 *
 * Automatically registers JSON converter given jackson dependency in classpath
 * Automatically enables Java<->JSON object conversions
 * Automatically assumes you want JSON returned
 */

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
@PropertySource(value = Array("classpath:/application.properties"))
@EnableWebMvc
class AppConfig extends WebMvcConfigurer {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper()
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule())

  @Bean
  def xmlMapper: ObjectMapper = new XmlMapper()
    .enable(SerializationFeature.INDENT_OUTPUT)
    .registerModule(DefaultScalaModule)
    .registerModule(new JavaTimeModule)

  @Bean
  def mappingJackson2HttpMessageConverter(): MappingJackson2HttpMessageConverter = {
    new MappingJackson2HttpMessageConverter(objectMapper)
  }

  @Bean
  def mappingJackson2XmlHttpMessageConverter(): MappingJackson2XmlHttpMessageConverter = {
    new MappingJackson2XmlHttpMessageConverter(xmlMapper)
  }

  override def configureMessageConverters(converters: JList[HttpMessageConverter[_]]): Unit = {
    converters.add(mappingJackson2XmlHttpMessageConverter())
    converters.add(mappingJackson2HttpMessageConverter())
  }

  override def configureContentNegotiation(configurer: ContentNegotiationConfigurer): Unit = {
    configurer.defaultContentType(MediaType.APPLICATION_JSON)
  }
}
