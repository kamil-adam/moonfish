package pl.scalare.main

import javax.validation.Valid

import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty

import scala.beans.BeanProperty

class ScalareConfiguration(
                            @BeanProperty @NotEmpty var template: String,
                            @BeanProperty @NotEmpty var defaultName: String = "Stranger")
  extends Configuration {
  @BeanProperty
  @Valid
  //  @NotNull
  var factory = new DataSourceFactory()
  //  @NotNull
  @BeanProperty
  @Valid
  var jerseyClient = new JerseyClientConfiguration()

  def this() = this(null, null)
}