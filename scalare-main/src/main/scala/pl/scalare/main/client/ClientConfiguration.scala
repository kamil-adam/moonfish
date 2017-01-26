package pl.scalare.main.client

import scala.beans.BeanProperty

import io.dropwizard.Configuration
import io.dropwizard.client.HttpClientConfiguration
import io.dropwizard.client.JerseyClientConfiguration
import javax.validation.Valid

class ClientConfiguration extends Configuration {
  //  @NotNull
  @BeanProperty
  @Valid
  var httpClient = new HttpClientConfiguration()

  //  @NotNull
  @BeanProperty
  @Valid
  var jerseyClient = new JerseyClientConfiguration()

}