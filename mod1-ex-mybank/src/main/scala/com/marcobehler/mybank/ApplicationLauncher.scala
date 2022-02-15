package com.marcobehler.mybank

import com.marcobehler.mybank.web.TransactionServlet
import org.apache.catalina.startup.Tomcat

object ApplicationLauncher {
  final val TOMCAT_PORT: Int = System.getProperty("server.port", "8080").toInt

  def main(args: Array[String]): Unit = {
    val tomcat = new Tomcat
    tomcat.setPort(TOMCAT_PORT)
    tomcat.getConnector

    val context = tomcat.addContext("", null)
    val servlet = Tomcat.addServlet(context, "myBank", new TransactionServlet())
    servlet.addMapping("/")
    servlet.setLoadOnStartup(1)

    tomcat.start()
  }
}
