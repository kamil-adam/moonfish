package pl.scalare.main.app

import java.util.{ Map => jMap }

import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.health.HealthCheckRegistry
import com.google.inject.{ Guice, Injector }
import com.typesafe.scalalogging.LazyLogging
import io.dropwizard.Application
import io.dropwizard.auth.{ AuthDynamicFeature, AuthValueFactoryProvider }
import io.dropwizard.jersey.setup.JerseyEnvironment
import io.dropwizard.jetty.setup.ServletEnvironment
import io.dropwizard.lifecycle.setup.LifecycleEnvironment
import io.dropwizard.setup.{ AdminEnvironment, Bootstrap, Environment }
import io.dropwizard.views.ViewBundle
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature
import pl.scalare.core.repo.HealthCheckRepo
import pl.scalare.main.healthchecks.{ EventHealthCheck, SQLiteHealthCheck, TemplateHealthCheck }
import pl.scalare.rest.User
import pl.scalare.rest.resources._
import pl.scalare.rest.servlets.ScalareServlet
import pl.scalare.rest.tasks.TaskTruncate

class ScalareApplication extends Application[ScalareConfiguration] with LazyLogging {

  override def getName: String = "ScalaRE"

  override def initialize(bootstrap: Bootstrap[ScalareConfiguration]) {
    logger info "initialize"

    bootstrap.addBundle(new ViewBundle[ScalareConfiguration]())
    bootstrap.addBundle(new ViewBundle[ScalareConfiguration]() {
      override def getViewConfiguration(c: ScalareConfiguration): jMap[String, jMap[String, String]] = {
        return super.getViewConfiguration(c)
      }
    })

    //    bootstrap.addCommand(new ScalareCommand())
  }

  override def run(c: ScalareConfiguration, e: Environment) {
    logger info "run"
    val i = Guice.createInjector(new ScalareModule(c, e))
    run(i, e)
  }

  private def run(i: Injector, e: Environment) {
    //environment
    run(i, e.jersey())
    run(i, e.lifecycle())
    run(i, e.servlets())
    run(i, e.admin())
    //metric
    run(i, e.healthChecks())
    run(i, e.metrics())
    //other
    //    e.jersey().register(new IllegalArgumentExceptionMapper(e.metrics()));
    //    e.jersey().register(new Resource());
    e.getApplicationContext.addServlet(classOf[ScalareServlet], "ScalareServlet")

  }

  private def run(i: Injector, e: JerseyEnvironment): Unit = {

    e.register(i.getInstance(classOf[AuthDynamicFeature]))
    e.register(classOf[RolesAllowedDynamicFeature]);
    //If you want to use @Auth to inject a custom Principal type into your resource
    e.register(new AuthValueFactoryProvider.Binder(classOf[User]));

    //
    //e.register(i.getInstance(classOf[ScalareResource]))
    e.register(i.getInstance(classOf[DatabaseResource]))
    e.register(i.getInstance(classOf[TaskResource]))
    e.register(i.getInstance(classOf[HealthCheckResource]))
    e.register(i.getInstance(classOf[EventResource]))
    e.register(i.getInstance(classOf[OmnibusResource]))

  }

  private def run(i: Injector, e: AdminEnvironment): Unit = {
    e.addTask(i.getInstance(classOf[TaskTruncate]))
  }

  private def run(i: Injector, e: LifecycleEnvironment): Unit = {

  }

  private def run(i: Injector, e: ServletEnvironment): Unit = {

  }

  private def run(i: Injector, e: MetricRegistry): Unit = {
  }

  def run(i: Injector, e: HealthCheckRegistry): Unit = {
    e.register("event", i.getInstance(classOf[EventHealthCheck]))
    e.register("template", i.getInstance(classOf[TemplateHealthCheck]))
    e.register("sqlite", i.getInstance(classOf[SQLiteHealthCheck]))
    val repo = i.getInstance(classOf[HealthCheckRepo])
    logger.info("runHealthChecks" + repo.runHealthChecks)

  }

}
