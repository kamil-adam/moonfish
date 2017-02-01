package pl.scalare.main.configuration

import javax.validation.Valid

import scala.beans.BeanProperty

/**
  * Created by enkime on 01.02.17.
  */
class TemplateHCConfiguration {
  @BeanProperty
  @Valid
  var template : String = _

}
