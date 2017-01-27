package pl.scalare.rest

import java.util.concurrent.atomic.AtomicLong

import com.codahale.metrics.annotation.Timed

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

import javax.ws.rs.core.MediaType
import java.util.Optional

import pl.scalare.core.repo.DatabaseRepo

@Path("/rest/database")
@Produces(Array(MediaType.APPLICATION_JSON))
class DatabaseResource(val repo : DatabaseRepo) {

  @GET()
  @Path("/")
  @Timed
  def database = repo.database
    
  @GET()
  @Path("/{database}/information")
  @Timed  
  def information(@PathParam("database") database :String) = repo.information(database)
}