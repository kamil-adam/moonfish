package pl.scalare.main.configuration

import javax.validation.Valid

import scala.beans.BeanProperty

class TemplateHCConfiguration {
  @BeanProperty
//  @Valid
  var templateString: String = _

  @BeanProperty
  var template2String: String = _

}
