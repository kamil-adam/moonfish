package pl.scalare.impl.repo

import javax.inject.Inject

import com.codahale.metrics.health.{HealthCheck, HealthCheckRegistry}
import pl.scalare.core.repo.HealthCheckRepo

import scala.collection.JavaConverters._

class HealthCheckRepoImpl @Inject()(val registery: HealthCheckRegistry) extends HealthCheckRepo {
  override def runHealthCheckList: List[(String, HealthCheck.Result)] = runHealthChecks.toList

  override def runHealthChecks: Map[String, HealthCheck.Result] = registery.runHealthChecks().asScala.toMap
}
