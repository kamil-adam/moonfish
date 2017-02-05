package pl.scalare.main

import javax.inject.Inject

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import pl.scalare.main.configuration.TemplateHCConfiguration

class TemplateHealthCheck@Inject()(@Inject val template: TemplateHCConfiguration) extends HealthCheck {

  override def check(): Result = {
    val saying = String.format(template.templateString, "TEST");
    return if (!saying.contains("TEST"))
      Result.unhealthy("template doesn't include a name")
    else
      Result.healthy();
  }
}