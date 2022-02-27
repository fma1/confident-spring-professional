package com.marcobehler.myfancypdfinvoices.web

import javax.validation.constraints.{NotBlank, Size}
import scala.beans.BeanProperty

class LoginForm {
  @BeanProperty
  @NotBlank
  @Size(min = 5, max = 7)
  var username: String = _
  @BeanProperty
  @NotBlank
  @Size(min = 5, max = 7)
  var password: String = _
}
