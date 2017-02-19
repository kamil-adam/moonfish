package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.TaskRepo
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.views.TasksView

@Path("/tasks")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class TaskResource @Inject()(@Inject val repo: TaskRepo, @Inject val view: ViewConfiguration) {

  @GET
  @Path("/")
  @Timed
  def tasks = repo.tasks.toArray

  @GET
  @Path("/view")
  @Timed
  def tasksView = new TasksView(view, tasks)
}
