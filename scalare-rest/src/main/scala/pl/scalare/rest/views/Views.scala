package pl.scalare.rest.views

import io.dropwizard.views.View
import pl.scalare.core.model.{Database, Select}
import pl.scalare.rest.RuntimeConfiguration

class DatabasesView(val rtc : RuntimeConfiguration, val databases: Array[String]) extends View("databases.mustache") {
}

class KeysView(val rtc : RuntimeConfiguration,  val key:Array[String]) extends View("key.mustache") {}

class SelectView(val rtc : RuntimeConfiguration, val table:Select) extends View("select.mustache") {}
