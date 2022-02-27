package com.marcobehler.myfancypdfinvoices.context

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.marcobehler.myfancypdfinvoices.ApplicationLauncher
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration, PropertySource, Scope}
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

@Configuration
@ComponentScan(basePackageClasses = Array(classOf[ApplicationLauncher]))
@PropertySource(Array("classpath:/application.properties"))
@PropertySource(value = Array("classpath:/application-${spring.profiles.active}.properties")
  , ignoreResourceNotFound = true)
@EnableWebMvc
class AppConfig {
  @Bean
  def objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  // Needed for @RequestParam validation
  @Bean
  def methodValidationPostProcessor = new MethodValidationPostProcessor()

  @Bean
  def viewResolver: ThymeleafViewResolver = {
    val viewResolver = new ThymeleafViewResolver()
    viewResolver.setTemplateEngine(templateEngine)

    viewResolver.setOrder(1)
    viewResolver.setViewNames(Array("*.html", "*.xhtml"))
    viewResolver
  }

  @Bean
  def templateEngine: SpringTemplateEngine = {
    val templateEngine = new SpringTemplateEngine()
    templateEngine.setTemplateResolver(templateResolver)
    templateEngine
  }

  @Bean
  def templateResolver: SpringResourceTemplateResolver = {
    val templateResolver = new SpringResourceTemplateResolver()
    templateResolver.setPrefix("classpath:/templates/")
    // Makes sense in development but not production
    templateResolver.setCacheable(false)
    templateResolver
  }
}
