package com.marcobehler.myfancypdfinvoices.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebsiteController {

  @GetMapping(Array("/"))
  def homepage(): String = {
    "index.html"
  }
}
