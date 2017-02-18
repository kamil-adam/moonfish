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

  override def toString = host + ":" + port + rootPath
}
