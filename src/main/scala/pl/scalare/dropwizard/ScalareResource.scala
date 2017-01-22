package pl.scalare.dropwizard

import java.util.concurrent.atomic.AtomicLong

import com.codahale.metrics.annotation.Timed

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

import javax.ws.rs.core.MediaType

@Path("/scalare")
@Produces(Array(MediaType.APPLICATION_JSON))
class ScalareResource (val template:String, val defaultName :String) {
    val counter = new AtomicLong()
    @GET
    @Timed
     def information(@QueryParam("name")  name: Option[String]): Information = {
        val value = String.format(template, name.getOrElse(defaultName));
        return new Information(counter.incrementAndGet(), value);
    }
}