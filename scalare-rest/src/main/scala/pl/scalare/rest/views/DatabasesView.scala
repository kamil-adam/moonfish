package pl.scalare.rest.views

import io.dropwizard.views.View

class DatabasesView (val databases: Array[Map[String, String]])
  extends View("databases.mustache") {

}
