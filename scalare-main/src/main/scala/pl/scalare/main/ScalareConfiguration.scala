package pl.scalare.main

import javax.validation.Valid

import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration
import io.dropwizard.db.DataSourceFactory
import pl.scalare.main.configuration.UrlConfiguration
import pl.scalare.rest.ViewConfiguration

import scala.beans.BeanProperty

class ScalareConfiguration extends Configuration {

  @BeanProperty
  //  @NotEmpty
  var view = new ViewConfiguration()

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