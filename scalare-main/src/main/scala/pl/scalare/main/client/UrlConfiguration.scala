package pl.scalare.main.client

import javax.validation.Valid

import scala.beans.BeanProperty

class UrlConfiguration {
  @BeanProperty
  @Valid
  var host : String = _
  @BeanProperty
  @Valid
  var port : Int = _
}
