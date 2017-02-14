package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.core.model. Table

class SelectView (val table:Table) extends View("select.mustache") {}