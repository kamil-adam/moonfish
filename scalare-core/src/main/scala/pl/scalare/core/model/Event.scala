package pl.scalare.core.model

import scala.beans.BeanProperty

class Event(@BeanProperty var id: Long,
            @BeanProperty var uuid: String,
            @BeanProperty var json: String) {
  def this() = this(0l, null, null)
}
