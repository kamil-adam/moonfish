package pl.scalare.main.app

import javax.validation.Validator

import com.codahale.metrics.MetricRegistry
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.Guice
import io.dropwizard.setup.Environment
import pl.scalare.spec.WhiteResultSpec

class ScalareModuleResultSpec extends WhiteResultSpec {
  "A ScalareModule" when {
//    val environment = stub[Environment]
    val name: String = ""
    val objectMapper: ObjectMapper = new ObjectMapper()
//    trait ValidatorTrait extends Validator
//    val validator/*: Validator*/ = stub[ValidatorTrait]
    val metricRegistry: MetricRegistry = new MetricRegistry
    val classLoader: ClassLoader = stub[ClassLoader]

    val environment = new Environment(name, objectMapper, null, metricRegistry, classLoader)
    val module = new ScalareModule(new ScalareConfiguration, null)
    "configure " should {
      "return ?" in {
//        val i = Guice.createInjector(module)
      }
    }
  }
}