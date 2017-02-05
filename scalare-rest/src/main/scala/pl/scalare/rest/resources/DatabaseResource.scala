package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.DatabaseRepo
import pl.scalare.rest.views.DatabasesView

@Path("/database")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class DatabaseResource @Inject()(@Inject val repo: DatabaseRepo) {

  @GET
  @Path("/")
  @Timed
  def databases = repo.databases

  @GET
  @Path("/view")
  @Timed
  def databasesView = new DatabasesView(databases)

  @GET
  @Path("/{database}/information")
  @Timed
  def information(@PathParam("database") database: String) = repo.information(database).toArray

  @GET
  @Path("/{database}/information/view")
  @Timed
  def informationView(@PathParam("database") database: String) = repo.information(database).toArray
}
