package com.marcobehler.mybank.dto

import scala.beans.BeanProperty

class ErrorDTO(@BeanProperty var errorMsg: String, @BeanProperty var invalidFields: List[String])
