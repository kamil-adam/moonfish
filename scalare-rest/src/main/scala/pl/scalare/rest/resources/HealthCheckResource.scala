package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import com.codahale.metrics.annotation.{ExceptionMetered, Metered, Timed}
import org.javatuples.Pair
import pl.scalare.core.repo.HealthCheckRepo
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.views.HealthCheckView

@Path("/hcs")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class HealthCheckResource @Inject()(@Inject val repo: HealthCheckRepo, @Inject val view: ViewConfiguration) {
  @GET
  @Path("/view")
  @Timed @Metered @ExceptionMetered
  def checksView = new HealthCheckView(view, checks)

  @GET
  @Path("/")
  @Timed @Metered @ExceptionMetered
  def checks = repo.runHealthCheckList.map(t => new Pair(t._1, t._2)).toArray
}
