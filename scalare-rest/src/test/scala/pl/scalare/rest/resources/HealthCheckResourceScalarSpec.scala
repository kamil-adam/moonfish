package pl.scalare.rest.resources

import com.codahale.metrics.health.HealthCheckRegistry
import pl.scalare.impl.repo.HealthCheckRepoImpl
import pl.scalare.rest.ViewConfiguration
import pl.scalare.spec.GrayScalarSpec

class HealthCheckResourceScalarSpec extends GrayScalarSpec {
  describe("A HealthCheckRegistry") {
    val registry = new HealthCheckRegistry()
    val repo = new HealthCheckRepoImpl(registry)
    val conf = new ViewConfiguration
    val resource = new HealthCheckResource(repo, conf)
    it("when invoke checks") {
      resource.checks.length should be >= 0
    }
    it("when invoke checksView") {
      val view = resource.checksView
      view.checks.length should be >= 0
    }
  }
}
