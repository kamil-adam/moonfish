package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs._

import com.codahale.metrics.annotation.{ ExceptionMetered, Metered, Timed }
import pl.scalare.core.repo.EventRepo
import pl.scalare.rest.views.EventsView

@Path("/event")
@Produces(Array(MediaType.APPLICATION_JSON))
class EventResource @Inject() (@Inject val repo: EventRepo) {
  @GET()
  @Path("/view")
  @Timed @Metered @ExceptionMetered
  def eventsView = new EventsView(events)

  @GET()
  @Path("/")
  @Timed @Metered @ExceptionMetered
  def events = repo.getAll.toArray

  @GET()
  @Path("/{id}")
  @Timed @Metered @ExceptionMetered
  def event(@PathParam("id") id: String) = repo.findByUuid(id)

  @POST()
  @Path("/")
  @Timed @Metered @ExceptionMetered
  def echo(content: String) = content

  @POST()
  @Path("/{id}")
  @Timed @Metered @ExceptionMetered
  def putEvent(@PathParam("id") id: String, content: String) = repo.insert(0, id, content)

}
