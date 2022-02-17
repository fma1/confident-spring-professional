package com.marcobehler.myfancypdfinvoices

import com.marcobehler.myfancypdfinvoices.web.MyFancyPdfInvoicesServlet
import org.apache.catalina.startup.Tomcat

object ApplicationLauncher {
  // System.setProperty("spring.profiles.active", "dev")

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat()
    tomcat.setPort(8080)
    tomcat.getConnector

    val ctx = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(ctx, "myFirstServlet", new MyFancyPdfInvoicesServlet())
    servlet.setLoadOnStartup(1)
    servlet.addMapping("/*")

    tomcat.start()
  }
}

class ApplicationLauncher {}