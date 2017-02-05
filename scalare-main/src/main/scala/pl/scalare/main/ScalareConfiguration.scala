package pl.scalare.main

import javax.validation.Valid

import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty
import pl.scalare.main.configuration.{TemplateHCConfiguration, UrlConfiguration}

import scala.beans.BeanProperty

class ScalareConfiguration extends Configuration {

  @BeanProperty
  @NotEmpty
  var defaultName: String = "Stranger"

  @BeanProperty
  //  @NotEmpty
  //  @Valid
  var template1 = new TemplateHCConfiguration()

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