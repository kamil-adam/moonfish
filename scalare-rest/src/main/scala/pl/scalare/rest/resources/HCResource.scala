package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.{GET, Path, Produces}
import javax.ws.rs.core.MediaType

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.HCRepo
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.views.{HCView}
import org.javatuples.Pair

@Path("/hcs")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class HCResource @Inject()(@Inject val repo: HCRepo, @Inject val view: ViewConfiguration) {
  @GET
  @Path("/")
  @Timed
  def hcs = repo.runHealthCheckList.map(t => new Pair(t._1, t._2)).toArray


  @GET
  @Path("/view")
  @Timed
  def hcsView = new HCView(view, hcs)
}
