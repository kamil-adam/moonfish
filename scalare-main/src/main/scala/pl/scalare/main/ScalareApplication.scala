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

    //    bootstrap.addBundle(new ViewBundle[ViewsConfiguration]())
    bootstrap.addBundle(new ViewBundle[ScalareConfiguration]() {
      override def getViewConfiguration(c: ScalareConfiguration): java.util.Map[String, java.util.Map[String, String]] = {
        return super.getViewConfiguration(c)
      }
    })
  }

  override def run(c: ScalareConfiguration, e: Environment) {
    logger info "run"

    val resource = new ScalareResource(c.template, c.defaultName);
    val healthCheck = new TemplateHealthCheck(c.template);
    e.healthChecks().register("template", healthCheck);

    e.jersey().register(resource);

    val factory = new DBIFactory()
    val jdbi = factory.build(e, c.factory, "sqlite")
    //    val dao = jdbi.onDemand(classOf[UserDAO]);
    //    environment.jersey().register(new UserResource(dao));


    val omnibusClient = new JerseyClientBuilder(e).using(c.jerseyClient)
      .build("omnibusClient")
    e.jersey().register(new OmnibusResource(omnibusClient))

  }
}
