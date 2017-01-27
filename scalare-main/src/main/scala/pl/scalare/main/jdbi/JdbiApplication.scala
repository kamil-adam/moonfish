package pl.scalare.main.jdbi

import io.dropwizard.Application
import pl.scalare.main.ScalareResource
import com.typesafe.scalalogging.LazyLogging
import pl.scalare.main.TemplateHealthCheck
import io.dropwizard.setup.Environment
import pl.scalare.main.ScalareConfiguration
import io.dropwizard.setup.Bootstrap
import io.dropwizard.jdbi.DBIFactory

class JdbiApplication extends Application[JdbiConfiguration] with LazyLogging {

  override def initialize(bootstrap: Bootstrap[JdbiConfiguration]) {
    logger info "initialize"
  }

  override def run(c: JdbiConfiguration, e: Environment) {
    logger info "run"

    val factory = new DBIFactory()
    val jdbi = factory.build(e, c.factory, "sqlite")
//    val dao = jdbi.onDemand(classOf[UserDAO]);
//    environment.jersey().register(new UserResource(dao));
  }

}