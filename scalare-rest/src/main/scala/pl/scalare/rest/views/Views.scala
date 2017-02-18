package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.core.model.Select
import pl.scalare.rest.ViewConfiguration

class DatabasesView(val rtc: ViewConfiguration, val databases: Array[String]) extends View("databases.mustache") {
}

class KeysView(val rtc: ViewConfiguration, val keys: Array[String]) extends View("keys.mustache") {}

class SelectView(val rtc: ViewConfiguration, val table: Select) extends View("select.mustache") {}
