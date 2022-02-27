package com.marcobehler.myfancypdfinvoices.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{GetMapping, RequestParam}

import java.util.Date

@Controller
class WebsiteController {

  @GetMapping(Array("/"))
  def homepage(model: Model, @RequestParam(required = false, defaultValue = "stranger") username: String): String = {
    model.addAttribute("username", username)
    model.addAttribute("currentDate", new Date())
    "index.html"
  }
}
