package pl.scalare.main

import javax.validation.Valid

import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty
import pl.scalare.main.client.UrlConfiguration

import scala.beans.BeanProperty

class ScalareConfiguration(
                            @BeanProperty @NotEmpty var template: String,
                            @BeanProperty @NotEmpty var defaultName: String = "Stranger")
  extends Configuration {

  def this() = this(null, null)

  @BeanProperty
  @Valid
  var sqlite = new DataSourceFactory()



  @BeanProperty
  @Valid
  var omnibusClient = new JerseyClientConfiguration()

  @BeanProperty
  @Valid
  var omnibusUrl = new UrlConfiguration()

}