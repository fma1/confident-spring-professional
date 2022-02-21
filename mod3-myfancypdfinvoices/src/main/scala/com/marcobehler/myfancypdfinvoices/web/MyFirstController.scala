package com.marcobehler.myfancypdfinvoices.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, ResponseBody}

@Controller
class MyFirstController {

  /*
   * ResponseBody writes to HttpServletOutputStream
   */
  @GetMapping(Array("/"))
  @ResponseBody
  def index(): String = {
    "Hello World"
  }
}
