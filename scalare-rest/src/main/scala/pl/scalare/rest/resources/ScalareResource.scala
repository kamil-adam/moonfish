package pl.scalare.rest.resources

import java.util.Optional
import java.util.concurrent.atomic.AtomicLong
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import com.codahale.metrics.annotation.Timed
import pl.scalare.japi.Information

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