package pl.scalare.main

import javax.inject.Inject

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

class TemplateHealthCheck(@Inject val template: String) extends HealthCheck {

  override def check(): Result = {
    val saying = String.format(template, "TEST");
    return if (!saying.contains("TEST"))
      Result.unhealthy("template doesn't include a name")
    else
      Result.healthy();
  }
}