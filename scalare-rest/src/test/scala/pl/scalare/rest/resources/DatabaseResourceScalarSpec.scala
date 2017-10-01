package pl.scalare.rest.resources

import pl.scalare.core.repo.SelectRepo
import pl.scalare.impl.repo.SelectRepoFake
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.asap.DatabaseAsap
import pl.scalare.spec.GrayScalarSpec

class DatabaseResourceScalarSpec extends GrayScalarSpec {
  describe("A DatabaseResourceScalarSpec") {
    it("when invoke databases") {
      val repo = new SelectRepoFake()
      val conf = new ViewConfiguration
      val rsrc = new DatabaseResource(repo, conf)
      rsrc.databases
    }
  }
}
