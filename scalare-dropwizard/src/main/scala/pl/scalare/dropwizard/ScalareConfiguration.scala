package pl.scalare.dropwizard

import scala.beans.BeanProperty

import org.hibernate.validator.constraints.NotEmpty

import io.dropwizard.Configuration

class ScalareConfiguration(
  @BeanProperty @NotEmpty var template: String,
  @BeanProperty @NotEmpty var defaultName: String = "Stranger")
    extends Configuration {
  def this() = this(null, null)

}