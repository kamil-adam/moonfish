package pl.scalare.main.configuration

import scala.beans.BeanProperty

class TemplateHCConfiguration {
  @BeanProperty //  @Valid
  var templateString: String = _

  @BeanProperty
  var template2String: String = _

}
