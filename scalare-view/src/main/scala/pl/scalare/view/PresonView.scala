package pl.scalare.main.views

import io.dropwizard.views.View

class PresonView(val person: Person)
  extends View("person.ftl") {

}