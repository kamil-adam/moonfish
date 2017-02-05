package pl.scalare.main.exceptionmapper

import javax.ws.rs.core.{MediaType, Response}
import javax.ws.rs.core.Response.Status
import javax.ws.rs.ext.ExceptionMapper

import com.codahale.metrics.{Meter, MetricRegistry}
import io.dropwizard.jersey.errors.ErrorMessage

class IllegalArgumentExceptionMapper(val exceptions: Meter) extends ExceptionMapper[IllegalArgumentException] {

  def this(metrics: MetricRegistry) = this(metrics.meter(getClass() + "exceptions"))

  override def toResponse(exception: IllegalArgumentException): Response = {
    exceptions.mark();
    return Response.status(Status.BAD_REQUEST)
      .header("X-YOU-SILLY", "true")
      .`type`(MediaType.APPLICATION_JSON_TYPE)
      .entity(new ErrorMessage(Status.BAD_REQUEST.getStatusCode(),
        "You passed an illegal argument!"))
      .build();
  }
}
