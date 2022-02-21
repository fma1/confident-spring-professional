package com.marcobehler.myfancypdfinvoices.dto

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty

class InvoiceDTO {
  @JsonProperty("user_id")
  @BeanProperty
  var userId: String = _
  @BeanProperty
  var amount: Integer = _
}
