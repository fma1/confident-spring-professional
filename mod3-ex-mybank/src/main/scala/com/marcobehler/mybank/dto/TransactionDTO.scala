package com.marcobehler.mybank.dto

import javax.validation.constraints.{NotBlank, NotNull}
import scala.beans.BeanProperty

class TransactionDTO {
  @BeanProperty
  @NotNull
  var amount: BigDecimal = _

  @BeanProperty
  @NotBlank
  var reference: String = _
}
