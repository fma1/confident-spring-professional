package com.marcobehler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class MyFirstServlet extends HttpServlet {
  val invoiceService = new InvoiceService()
  val objectMapper: ObjectMapper = new ObjectMapper().registerModule(DefaultScalaModule)

  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/")) {
      resp.setContentType("text/html; charset=UTF-8");
      resp.getWriter.print(
        "<html>\n" +
          "<body>\n" +
          "<h1>Hello World</h1>\n" +
          "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
          "</body>\n" +
          "</html>");
    } else if (req.getRequestURI.equalsIgnoreCase("/invoices")) {
      resp.setContentType("application/json; charset=UTF-8")
      val invoices = invoiceService.findAll()
      resp.getWriter.print(objectMapper.writeValueAsString(invoices))
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    if (req.getRequestURI.equalsIgnoreCase("/invoices")) {
      // TODO: Validation checks since the user might not have sended this information in
      val userId: String = req.getParameter("user_id");
      val amount: Int = Integer.valueOf(req.getParameter("amount"));

      val invoice = invoiceService.create(userId, amount);

      resp.setContentType("application/json; charset=UTF-8");
      val json = objectMapper.writeValueAsString(invoice);
      resp.getWriter.print(json);
    } else {
      resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }
}
