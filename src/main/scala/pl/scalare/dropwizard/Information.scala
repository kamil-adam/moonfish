package pl.scalare.dropwizard

import org.hibernate.validator.constraints.Length
import scala.beans.BeanProperty

class Information(@BeanProperty var id: Long, @BeanProperty @Length(max = 3) var content: String) {
  def this() = this(0l, null)
}