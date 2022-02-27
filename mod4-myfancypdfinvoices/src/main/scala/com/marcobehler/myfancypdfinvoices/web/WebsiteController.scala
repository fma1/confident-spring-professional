package com.marcobehler.myfancypdfinvoices.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.{GetMapping, ModelAttribute, PostMapping, RequestParam}

import java.util.Date
import javax.validation.Valid

@Controller
class WebsiteController {

  @GetMapping(Array("/"))
  def homepage(model: Model, @RequestParam(required = false, defaultValue = "stranger") username: String): String = {
    model.addAttribute("username", username)
    model.addAttribute("currentDate", new Date())
    "index.html"
  }

  @GetMapping(Array("/login"))
  def login(model: Model): String = {
    model.addAttribute("loginForm", new LoginForm())
    "login.html"
  }

  @PostMapping(Array("/login"))
  def login(@ModelAttribute @Valid loginForm: LoginForm, bindingResult: BindingResult, model: Model): String = {
    if (bindingResult.hasErrors) {
      "login.html"
    // Faking login and assuming username == password is a correct login combination
    } else if (loginForm.username.equals(loginForm.password)) {
      "redirect:/"
    } else {
      model.addAttribute("invalidCredentials", "true")
      "login.html"
    }
  }
}
