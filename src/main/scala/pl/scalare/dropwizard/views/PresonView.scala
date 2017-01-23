package pl.scalare.dropwizard.views

import io.dropwizard.views.View

class PresonView(val person: Person)
    extends View("person.ftl") {

}