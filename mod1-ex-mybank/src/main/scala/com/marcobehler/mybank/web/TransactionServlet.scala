package com.marcobehler.mybank.web

import com.marcobehler.mybank.context.Application.{objectMapper, transactionService}

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class TransactionServlet extends HttpServlet {
  override def doGet(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    req.getRequestURI.toLowerCase match {
      case "/" =>
        resp.setContentType("text/html charset=UTF-8")
        resp.getWriter.print(
          """
            |<html>
            |<head><title>Bank Homepage</title></head>
            |<body>
            |<h2>Welcome to The Bank!</h2>
            |</body>
            |</html>
            |""".stripMargin)
      case "/transaction" =>
        val id = req.getParameter("id")
        Option(transactionService.findById(id)) match {
          case Some(transaction) =>
            resp.setContentType("application/json charset=UTF-8")
            resp.getWriter.print(objectMapper.writeValueAsString(transaction))
          case None =>
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
        }
      case "/transactions" =>
        val id = req.getParameter("id")
        Option(transactionService.findAll()) match {
          case Some(transactions) =>
            resp.setContentType("application/json charset=UTF-8")
            resp.getWriter.print(objectMapper.writeValueAsString(transactions))
          case None =>
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
        }
      case _ =>
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse): Unit = {
    req.getRequestURI.toLowerCase match {
      case "/transactions" =>
        val amount = BigDecimal(req.getParameter("amount"))
        val reference = req.getParameter("reference")
        val transaction = transactionService.create(amount, reference)
        resp.getWriter.print(objectMapper.writeValueAsString(transaction))
      case _ =>
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND)
    }
  }
}
