package pl.scalare.main.client

import io.dropwizard.client.HttpClientBuilder
import io.dropwizard.setup.{Bootstrap, Environment}
import pl.scalare.main.ApplicationLogging

class ClientApplication extends ApplicationLogging[ClientConfiguration] {

  override def initialize(bootstrap: Bootstrap[ClientConfiguration]) {
    logger info "initialize"
  }

  override def run(c: ClientConfiguration, e: Environment) {
    logger info "run"

    val httpClient = new HttpClientBuilder(e).using(c.httpClient).build("httpClient")
    //    environment.jersey().register(new ExternalServiceResource(httpClient));

  }

}