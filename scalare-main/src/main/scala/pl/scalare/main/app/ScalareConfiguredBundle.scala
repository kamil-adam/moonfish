package pl.scalare.main.app

import io.dropwizard.ConfiguredBundle
import io.dropwizard.setup.{Bootstrap, Environment}

abstract class ScalareConfiguredBundle extends ConfiguredBundle[ScalareConfiguredBundleConfiguration] {

  override def run(c: ScalareConfiguredBundleConfiguration, e: Environment) {
    c.getConfiguredBundleConfiguration
  }

  def initialize(bootstrap: Bootstrap[_]): Unit = {}
}

trait ScalareConfiguredBundleConfiguration {

  def getConfiguredBundleConfiguration: String

}
