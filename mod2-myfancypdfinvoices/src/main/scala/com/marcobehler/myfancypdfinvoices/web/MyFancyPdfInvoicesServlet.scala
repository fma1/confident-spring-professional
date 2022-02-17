package com.marcobehler.myfancypdfinvoices.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.marcobehler.myfancypdfinvoices.context.MyFancyPdfInvoicesConfig
import com.marcobehler.myfancypdfinvoices.services.{InvoiceService, UserService}
import org.springframework.context.annotation.AnnotationConfigApplicationContext

import javax.servlet.ServletConfig
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFancyPdfInvoicesServlet extends HttpServlet {
  var userService: UserService = _
  var invoiceService: InvoiceService = _
  var objectMapper: ObjectMapper = _

  override def init(config: ServletConfig): Unit = {
    val ctx = new AnnotationConfigApplicationContext(classOf[MyFancyPdfInvoicesConfig])
    userService = ctx.getBean(classOf[UserService])
    invoiceService = ctx.getBean(classOf[InvoiceService])
    objectMapper = ctx.getBean(classOf[ObjectMapper])
  }

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/")) {
      resp.setContentType("text/html charset=UTF-8")
      resp.getWriter.print(
        "<html>\n" +
          "<body>\n" +
          "<h1>Hello World</h1>\n" +
          "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
          "</body>\n" +
          "</html>")
    } else if (req.getRequestURI.equalsIgnoreCase("/invoices")) {
      resp.setContentType("application/json charset=UTF-8")
      val invoices = invoiceService.findAll()
      resp.getWriter.print(objectMapper.writeValueAsString(invoices))
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/invoices")) {
      // TODO: Validation checks since the user might not have sent this information in
      val userId: String = req.getParameter("user_id")
      val amount: Int = Integer.valueOf(req.getParameter("amount"))

      val invoice = invoiceService.create(userId, amount)

      resp.setContentType("application/json charset=UTF-8")
      val json = objectMapper.writeValueAsString(invoice)
      resp.getWriter.print(json)
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }
}
