package pl.scalare.core.model

import scala.beans.BeanProperty

class Database(@BeanProperty var name :String) {
  def this() = this(null)
}
