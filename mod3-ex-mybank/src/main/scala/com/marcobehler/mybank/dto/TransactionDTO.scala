package com.marcobehler.mybank.dto

import scala.beans.BeanProperty

class TransactionDTO {
  @BeanProperty
  var amount: BigDecimal = _

  @BeanProperty
  var reference: String = _
}
