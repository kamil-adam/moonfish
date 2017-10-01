package pl.scalare.rest.resources

import pl.scalare.impl.repo.TaskRepoFake
import pl.scalare.rest.ViewConfiguration
import pl.scalare.spec.GrayScalarSpec

class TaskResourceScalarSpec extends GrayScalarSpec {
  describe("A DatabaseResourceScalarSpec") {
    val repo = new TaskRepoFake()
    val conf = new ViewConfiguration
    val rsrc = new TaskResource(repo, conf)
    it("when invoke databases") {
      rsrc.tasks.length should be > 0
    }
  }
}
