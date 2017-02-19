package pl.scalare.impl.repo

import pl.scalare.core.repo.TaskRepo

class TaskRepoFake extends TaskRepo {

  def tasks = List("log-level", "truncate", "gc")

}
