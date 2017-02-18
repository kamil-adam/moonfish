package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{Path, Produces}

import pl.scalare.rest.ViewConfiguration

@Path("/scalare")
@Produces(Array(MediaType.APPLICATION_JSON))
class ScalareResource @Inject()(val view: ViewConfiguration) {

}