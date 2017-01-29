package pl.scalare.main.client

import javax.validation.Valid

import io.dropwizard.Configuration
import io.dropwizard.client.HttpClientConfiguration

import scala.beans.BeanProperty

class ClientConfiguration extends Configuration {
  //  @NotNull
  @BeanProperty
  @Valid
  var httpClient = new HttpClientConfiguration()


}