package pl.scalare.rest.asap

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.{Gson, JsonParser}
import org.fusesource.scalate.TemplateEngine
import pl.scalare.impl.repo.SelectRepoFake
import pl.scalare.rest.resources.DatabaseResource
import pl.scalare.util.{AnyMap, AppLogging}

object DatabaseAsap extends AppLogging {
  val repo = new SelectRepoFake
  val resource = new DatabaseResource(repo)
  val table = resource.selectView("hsql", "VIEWS")
  val mapper = new ObjectMapper
  val json = mapper.writeValueAsString(table)
  println(json)
  val parser = new JsonParser()
  val jsonObject = parser.parse(json).getAsJsonObject()
  val map = new GsonMap(jsonObject)
  println("" + map)
  val engine = new TemplateEngine
  val output = engine.layout("/pl/scalare/rest/views/select.mustache", map)
  println(output)
}
