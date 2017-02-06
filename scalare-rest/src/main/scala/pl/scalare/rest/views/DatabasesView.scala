package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.core.model.Database

class DatabasesView (val databases: Array[Database])
  extends View("databases.mustache") {

}
