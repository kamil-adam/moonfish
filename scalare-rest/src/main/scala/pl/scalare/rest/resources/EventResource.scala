package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.EventRepo
import pl.scalare.rest.views.EventsView

@Path("/event")
@Produces(Array(MediaType.APPLICATION_JSON))
class EventResource @Inject()(@Inject val repo: EventRepo) {
  @GET()
  @Path("/view")
  @Timed
  def eventsView = new EventsView(events)

  @GET()
  @Path("/")
  @Timed
  def events = repo.getAll.toArray

}
