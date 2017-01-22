package pl.scalare.dropwizard

import com.typesafe.scalalogging.LazyLogging

import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import pl.scalare.domain.AppLogging

class ScalareApplication extends Application[ScalareConfiguration] with LazyLogging {

  //  override def getName: String = "getClass"

  override def initialize(bootstrap: Bootstrap[ScalareConfiguration]) {
    logger info "initialize"
  }

  override def run(configuration: ScalareConfiguration, environment: Environment) {
    logger info "run"

    val resource = new ScalareResource(configuration.template, configuration.defaultName);
    val healthCheck = new TemplateHealthCheck(configuration.template);
    environment.healthChecks().register("template", healthCheck);

    environment.jersey().register(resource);
  }
}

object ScalareApplication extends AppLogging {
  new ScalareApplication().run(args: _*);
}