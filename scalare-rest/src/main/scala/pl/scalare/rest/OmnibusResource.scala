package pl.scalare.rest

import javax.ws.rs.Path
import javax.ws.rs.client.Client

import pl.scalare.core.client.{OmnibusProxy, OmnibusProxyImpl}

@Path("/rest/omnibus")
class OmnibusResource (val proxy : OmnibusProxy) {
  def this (client : Client) = this(new OmnibusProxyImpl(client))


}