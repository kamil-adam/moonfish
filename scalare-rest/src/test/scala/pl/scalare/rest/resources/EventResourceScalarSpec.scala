package pl.scalare.rest.resources

import pl.scalare.core.repo.EventRepo
import pl.scalare.rest.ViewConfiguration
import pl.scalare.spec.GrayScalarSpec

class EventResourceScalarSpec extends GrayScalarSpec {
  describe("A EventResource") {
    val repo:EventRepo = new EventRepoStub()
    val conf = new ViewConfiguration
    val resource = new EventResource(repo)
    it("when invoke events") {
      resource.events.length should be >= 0
    }
    it("when invoke eventsView") {
      val view = resource.eventsView
      view.events.length should be >= 0
    }
  }
}
