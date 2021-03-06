package com.marcobehler.mybank.model

import com.fasterxml.jackson.annotation.JsonFormat

import java.time.ZonedDateTime
import scala.beans.BeanProperty

case class Transaction(@BeanProperty var id: String,
                       @BeanProperty var amount: BigDecimal,
                       @BeanProperty @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ") var timestamp: ZonedDateTime,
                       @BeanProperty var reference: String)
