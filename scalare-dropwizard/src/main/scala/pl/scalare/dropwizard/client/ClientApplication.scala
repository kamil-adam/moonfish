package pl.scalare.dropwizard.client

import com.typesafe.scalalogging.LazyLogging

import io.dropwizard.Application
import io.dropwizard.client.HttpClientBuilder
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import pl.scalare.dropwizard.ApplicationLogging
import io.dropwizard.client.JerseyClientBuilder

class ClientApplication extends ApplicationLogging[ClientConfiguration] {

  override def initialize(bootstrap: Bootstrap[ClientConfiguration]) {
    logger info "initialize"
  }

  override def run(c: ClientConfiguration, e: Environment) {
    logger info "run"

    val httpClient = new HttpClientBuilder(e).using(c.httpClient).build("httpClient")
    //    environment.jersey().register(new ExternalServiceResource(httpClient));

    val jerseyClient = new JerseyClientBuilder(e).using(c.jerseyClient)
      .build("jerseyClient")
    //    environment.jersey().register(new ExternalServiceResource(client));

  }

}