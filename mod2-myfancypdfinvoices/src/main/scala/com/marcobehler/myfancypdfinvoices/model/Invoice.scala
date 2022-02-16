package com.marcobehler.myfancypdfinvoices.model

import java.util.UUID
import scala.beans.BeanProperty

case class Invoice(id: String = UUID.randomUUID().toString, @BeanProperty var userId: String, @BeanProperty var amount: Int, @BeanProperty var pdfUrl: String)
