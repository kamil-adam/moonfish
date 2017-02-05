package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.japi.Information

class InformationView(val person: Information)
  extends View("information.mustache") {

}