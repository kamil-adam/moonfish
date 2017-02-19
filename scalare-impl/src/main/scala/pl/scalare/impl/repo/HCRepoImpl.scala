package pl.scalare.impl.repo

import javax.inject.Inject

import com.codahale.metrics.health.{HealthCheck, HealthCheckRegistry}
import pl.scalare.core.repo.HCRepo

import scala.collection.JavaConverters._

class HCRepoImpl @Inject()(val registery : HealthCheckRegistry) extends HCRepo {
  override def runHealthChecks: Map[String, HealthCheck.Result] = registery.runHealthChecks().asScala.toMap

  override def runHealthCheckList: List[(String, AnyRef)] = runHealthChecks.toList
}
