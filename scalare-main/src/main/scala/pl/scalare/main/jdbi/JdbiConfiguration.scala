package pl.scalare.main.jdbi

import scala.beans.BeanProperty

import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.Valid

class JdbiConfiguration extends Configuration {
  @BeanProperty
  @Valid
//  @NotNull
  var factory = new DataSourceFactory()

}