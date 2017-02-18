package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{Path, Produces}

import pl.scalare.core.repo.SnapshotRepo

@Path("/snapshot")
@Produces(Array(MediaType.APPLICATION_JSON))
class SnapshotResource @Inject()(@Inject val repo: SnapshotRepo) {
  //  @GET()
  //  @Path("/view")
  //  @Timed
  //  def eventsView = new EventsView(events)
  //
  //  @GET()
  //  @Path("/")
  //  @Timed
  //  def events = repo.getAll.toArray

}
