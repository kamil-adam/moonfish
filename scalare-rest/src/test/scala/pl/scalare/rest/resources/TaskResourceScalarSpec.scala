package pl.scalare.rest.resources

import pl.scalare.impl.repo.TaskRepoFake
import pl.scalare.rest.ViewConfiguration
import pl.scalare.spec.GrayScalarSpec

class TaskResourceScalarSpec extends GrayScalarSpec {
  describe("A TaskResource") {
    val repo = new TaskRepoFake()
    val conf = new ViewConfiguration
    val resource = new TaskResource(repo, conf)
    it("when invoke tasks") {
      resource.tasks.length should be > 0
    }
    it("when invoke tasksView") {
      val view = resource.tasksView
      view.tasks.length should be >= 0
    }
  }
}
