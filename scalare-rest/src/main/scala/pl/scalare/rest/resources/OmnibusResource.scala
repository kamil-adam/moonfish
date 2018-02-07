package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.client.Client
import javax.ws.rs.core.MediaType
import javax.ws.rs.{ Path, Produces }

import pl.scalare.core.client.{ OmnibusProxy, OmnibusProxyImpl }

@Path("/omnibus")
@Produces(Array(MediaType.APPLICATION_JSON))
class OmnibusResource @Inject() (@Inject val proxy: OmnibusProxy) {
  def this(client: Client) = this(new OmnibusProxyImpl(client))
}
