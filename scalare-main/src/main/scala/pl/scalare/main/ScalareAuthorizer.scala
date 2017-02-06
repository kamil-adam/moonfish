package pl.scalare.main

import io.dropwizard.auth.Authorizer
import pl.scalare.rest.User

class ScalareAuthorizer extends Authorizer[User] {

  override def authorize( user: User, role: String) = user.getName().equals("good-guy") && role.equals("ADMIN")


}
