package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.main.views.Person

class PersonView(val person: Person)
  extends View("person.mustache") {

}