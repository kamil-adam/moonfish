package pl.scalare.main.healthchecks

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

class SQLiteHealthCheck extends HealthCheck {
  override def check(): Result = ???
}
