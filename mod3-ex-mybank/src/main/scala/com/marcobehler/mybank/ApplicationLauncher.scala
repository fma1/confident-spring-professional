package com.marcobehler.mybank

import com.marcobehler.mybank.context.AppConfig
import org.apache.catalina.startup.Tomcat
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

object ApplicationLauncher {
  final val TOMCAT_PORT: Int = System.getProperty("server.port", "8080").toInt

  def createApplicationContext(context: ServletContext): AnnotationConfigWebApplicationContext = {
    val ctx = new AnnotationConfigWebApplicationContext()
    ctx.register(classOf[AppConfig])
    // Set of methods to contact with Servlet Container
    // Servlet Container in this case is Tomcat
    ctx.setServletContext(context)
    ctx.refresh()
    ctx.registerShutdownHook()
    ctx
  }

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat
    tomcat.setPort(TOMCAT_PORT)
    tomcat.getConnector

    val tomcatContext = tomcat.addContext("", null)

    val appCtx = createApplicationContext(tomcatContext.getServletContext)
    val dispatcherServlet = new DispatcherServlet(appCtx)
    val servlet = Tomcat.addServlet(tomcatContext, "myBank", dispatcherServlet)
    servlet.addMapping("/")
    servlet.setLoadOnStartup(1)

    tomcat.start()
  }
}

class ApplicationLauncher {}
