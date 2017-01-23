package pl.scalare.dropwizard.views

import scala.beans.BeanProperty

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid


class ViewsConfiguration extends Configuration {
  //  @NotNull
  @BeanProperty
  @Valid
  var s: String = ""

}