package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.rest.ViewConfiguration

class TasksView(val view: ViewConfiguration, tasks: Array[String])
  extends View("tasks.mustache") {

}
