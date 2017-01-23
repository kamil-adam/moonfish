package pl.scalare.dropwizard

import com.codahale.metrics.health.HealthCheck.Result
import com.codahale.metrics.health.HealthCheck

class TemplateHealthCheck(val template: String) extends HealthCheck {

  override def check(): Result = {
    val saying = String.format(template, "TEST");
    return if (!saying.contains("TEST"))
      Result.unhealthy("template doesn't include a name")
    else
      Result.healthy();
  }
}