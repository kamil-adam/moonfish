package pl.scalare.rest

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.DatabaseRepo

@Path("/rest/database")
@Produces(Array(MediaType.APPLICATION_JSON))
class DatabaseResource @Inject()(@Inject val repo: DatabaseRepo) {

  @GET()
  @Path("/")
  @Timed
  //  def database:util.List[String] = repo.database.asJava
  def database = repo.database.toArray

  @GET()
  @Path("/{database}/information")
  @Timed
  def information(@PathParam("database") database: String) = repo.information(database).toArray
}