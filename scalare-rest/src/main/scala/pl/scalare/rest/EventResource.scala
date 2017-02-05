package pl.scalare.rest

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.EventRepo

@Path("/rest/events")
@Produces(Array(MediaType.APPLICATION_JSON))
class EventResource @Inject()(@Inject val repo: EventRepo) {
  @GET()
  @Path("/")
  @Timed
  //  def database:util.List[String] = repo.database.asJava
  def events = repo.getAll.toArray
}
