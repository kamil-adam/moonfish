package pl.scalare.rest

import scala.beans.BeanProperty

/**
  * Created by enkime on 17.02.17.
  */
class RuntimeConfiguration {
  @BeanProperty
  var hostname :String =_
  @BeanProperty
  var port :String =_

}
