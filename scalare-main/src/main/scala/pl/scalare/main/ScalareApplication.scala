package pl.scalare.main

import com.google.inject.{Guice, Injector}
import com.typesafe.scalalogging.LazyLogging
import io.dropwizard.Application
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.setup.{Bootstrap, Environment}
import io.dropwizard.views.ViewBundle
import pl.scalare.core.repo.DatabaseRepo
import pl.scalare.impl.repo.DatabaseRepoImpl
import pl.scalare.rest.{DatabaseResource, OmnibusResource, ScalareResource}
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
    val i = Guice.createInjector(new ScalareModule(c))
    run(i,e)

    val scalare = new ScalareResource("", c.defaultName)
    e.jersey().register(scalare)



    val factory = new DBIFactory()
    val sqlite = factory.build(e, c.sqlite, "sqlite")
    ////        val dao = jdbi.onDemand(classOf[UserDAO]);
    //    //    environment.jersey().register(new UserResource(dao));

    val omnibusClient = new JerseyClientBuilder(e).using(c.omnibusClient).build("omnibusClient")
    val omnibus = new OmnibusResource(omnibusClient)
    e.jersey().register(omnibus)
  }

  def run(i :Injector, e : Environment ) {
    e.jersey().register(i.getInstance(classOf[DatabaseResource]))

    e.healthChecks().register("template", i.getInstance(classOf[TemplateHealthCheck]))
  }
}
