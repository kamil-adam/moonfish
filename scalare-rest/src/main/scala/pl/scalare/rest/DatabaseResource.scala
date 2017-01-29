package pl.scalare.rest

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.repo.DatabaseRepo

@Path("/rest/database")
@Produces(Array(MediaType.APPLICATION_JSON))
class DatabaseResource(val repo: DatabaseRepo) {

  @GET()
  @Path("/")
  @Timed
  def database = repo.database

  @GET()
  @Path("/{database}/information")
  @Timed
  def information(@PathParam("database") database: String) = repo.information(database)
}