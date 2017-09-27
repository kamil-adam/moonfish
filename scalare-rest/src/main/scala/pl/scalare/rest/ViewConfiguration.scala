package pl.scalare.rest

import scala.beans.BeanProperty

class ViewConfiguration {
  @BeanProperty
  //  @Valid
  var host: String = _
  @BeanProperty
  //  @Valid
  var port: Int = _
  @BeanProperty
  //  @Valid
  var rootPath: String = _

  @BeanProperty
  var adminContextPath: String = _

  @BeanProperty
  var applicationContextPath: String = _

  override def toString = host + ":" + port + rootPath
}
