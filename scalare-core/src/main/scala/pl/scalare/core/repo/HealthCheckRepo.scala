package pl.scalare.core.repo

import com.codahale.metrics.health.HealthCheck

trait HealthCheckRepo {
  def runHealthChecks: Map[String,HealthCheck.Result]

  def runHealthCheckList: List[(String,HealthCheck.Result)]
}
