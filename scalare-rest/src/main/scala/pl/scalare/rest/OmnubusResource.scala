package pl.scalare.rest

import javax.ws.rs.client.Client

import pl.scalare.core.client.{OmnibusProxy, OmnibusProxyImpl}

class OmnibusResource (val proxy : OmnibusProxy) {
  def this (client : Client) = this(new OmnibusProxyImpl(client))
}
