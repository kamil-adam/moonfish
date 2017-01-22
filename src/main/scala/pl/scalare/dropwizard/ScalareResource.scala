package pl.scalare.dropwizard

import java.util.concurrent.atomic.AtomicLong

import com.codahale.metrics.annotation.Timed

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

import javax.ws.rs.core.MediaType
import java.util.Optional

@Path("/scalare")
@Produces(Array(MediaType.APPLICATION_JSON))
class ScalareResource(val template: String, val defaultName: String) {
  val counter = new AtomicLong()

  @GET()
  @Path("/information")
  @Timed
  def information(@QueryParam("name") name: Optional[String]): Information = {
    val nameOption = Option(name.orElse(null))
    val nameNotNull = nameOption.getOrElse(defaultName)
    val value = String.format(template, nameNotNull);
    return new Information(counter.incrementAndGet(), value);

  }
}