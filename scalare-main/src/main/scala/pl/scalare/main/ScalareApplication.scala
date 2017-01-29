package pl.scalare.main

import com.typesafe.scalalogging.LazyLogging
import io.dropwizard.Application
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.setup.{Bootstrap, Environment}
import io.dropwizard.views.ViewBundle
import pl.scalare.rest.OmnibusResource
import pl.scalare.util.AppLogging

object ScalareApplication extends AppLogging {
  new ScalareApplication().run(args: _*);
}

class ScalareApplication extends Application[ScalareConfiguration] with LazyLogging {

  //  override def getName: String = "getClass"

  override def initialize(bootstrap: Bootstrap[ScalareConfiguration]) {
    logger info "initialize"

    bootstrap.addBundle(new ViewBundle[ScalareConfiguration]())
    bootstrap.addBundle(new ViewBundle[ScalareConfiguration]() {
      override def getViewConfiguration(c: ScalareConfiguration): java.util.Map[String, java.util.Map[String, String]] = {
        return super.getViewConfiguration(c)
      }
    })
  }

  override def run(c: ScalareConfiguration, e: Environment) {
    logger info "run"

    val healthCheck = new TemplateHealthCheck(c.template)
    e.healthChecks().register("template", healthCheck)

    val scalare = new ScalareResource(c.template, c.defaultName)
    e.jersey().register(scalare);
//
    val factory = new DBIFactory()
    val sqlite = factory.build(e, c.sqlite, "sqlite")
////        val dao = jdbi.onDemand(classOf[UserDAO]);
//    //    environment.jersey().register(new UserResource(dao));
//
//
    val omnibusClient = new JerseyClientBuilder(e).using(c.omnibusClient).build("omnibusClient")
    val omnibus = new OmnibusResource(omnibusClient)
    e.jersey().register(omnibus)
  }
}
