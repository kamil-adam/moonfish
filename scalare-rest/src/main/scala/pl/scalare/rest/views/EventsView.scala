package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.core.model.Event

class EventsView(val events: Array[Event])
  extends View("events.mustache") {

}