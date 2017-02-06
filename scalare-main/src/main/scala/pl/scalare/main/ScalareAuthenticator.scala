package pl.scalare.main

import java.util.Optional

import io.dropwizard.auth.Authenticator
import io.dropwizard.auth.basic.BasicCredentials
import pl.scalare.rest.User

class BasicAuthenticator extends Authenticator[BasicCredentials, User] {
  override def authenticate(credentials: BasicCredentials): Optional[User] = {
    val bool = "password".equals(credentials.getPassword())
    val user = if (bool) new User(credentials.getUsername()) else null
    return Optional.ofNullable(user)
  }
}

class OAuthAuthenticator extends Authenticator[String, User] {
  override def authenticate(token: String): Optional[User] = {
    val bool = "password".equals(token)
    val user = if (bool) new User("") else null
    return Optional.ofNullable(user)
  }
}
