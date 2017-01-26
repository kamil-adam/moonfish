package pl.scalare.main.views

import io.dropwizard.Application
import pl.scalare.dropwizard.ScalareResource
import com.typesafe.scalalogging.LazyLogging
import pl.scalare.dropwizard.TemplateHealthCheck
import io.dropwizard.setup.Environment
import pl.scalare.dropwizard.ScalareConfiguration
import io.dropwizard.setup.Bootstrap
import io.dropwizard.views.ViewBundle

import java.util.Map

class ViewsApplication extends Application[ViewsConfiguration] with LazyLogging {

  override def initialize(bootstrap: Bootstrap[ViewsConfiguration]) {
    logger info "initialize"
    bootstrap.addBundle(new ViewBundle[ViewsConfiguration]())
    bootstrap.addBundle(new ViewBundle[ViewsConfiguration]() {
      override def getViewConfiguration(c: ViewsConfiguration): Map[String, Map[String, String]] = {
        return super.getViewConfiguration(c)
      }
    })
  }

  override def run(c: ViewsConfiguration, e: Environment) {
    logger info "run"
  }
}