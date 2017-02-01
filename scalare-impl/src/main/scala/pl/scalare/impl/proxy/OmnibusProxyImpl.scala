package pl.scalare.core.client

import javax.inject.Inject
import javax.ws.rs.client.Client

class OmnibusProxyImpl(@Inject val client: Client) extends OmnibusProxy {

}