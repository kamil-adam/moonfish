package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.rest.ViewConfiguration

import org.javatuples.Pair


class TasksView(val view: ViewConfiguration, val tasks: Array[String])
  extends View("tasks.mustache") {
}

class HCView(val view: ViewConfiguration, val hcs: Array[Pair[String,AnyRef]])
  extends View("hcs.mustache") {
}
