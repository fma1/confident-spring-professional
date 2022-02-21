package com.marcobehler.myfancypdfinvoices

import com.marcobehler.myfancypdfinvoices.context.MyFancyPdfInvoicesConfig
import org.apache.catalina.startup.Tomcat
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext

object ApplicationLauncher {
  System.setProperty("spring.profiles.active", "dev")

  def createApplicationContext(context: ServletContext): AnnotationConfigWebApplicationContext = {
    val ctx = new AnnotationConfigWebApplicationContext()
    ctx.register(classOf[MyFancyPdfInvoicesConfig])
    ctx.setServletContext(context)
    ctx.refresh()
    ctx.registerShutdownHook()
    ctx
  }

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val tomcatCtx = tomcat.addContext("", null)

    val appCtx = createApplicationContext(tomcatCtx.getServletContext)
    val dispatcherServlet = new DispatcherServlet(appCtx)
    val servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet)
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}

class ApplicationLauncher {}