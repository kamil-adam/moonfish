package pl.scalare.dropwizard

import org.hibernate.validator.constraints.Length

class Information(var id: Long, @Length(max = 3) var content: String) {
  def this() = this(0l, null)
}