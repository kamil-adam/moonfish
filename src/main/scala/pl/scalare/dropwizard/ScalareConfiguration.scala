package pl.scalare.dropwizard

import org.hibernate.validator.constraints.NotEmpty

import io.dropwizard.Configuration

class ScalareConfiguration(
  @NotEmpty var template: String,
  @NotEmpty var defaultName: String = "Stranger")
    extends Configuration {

}