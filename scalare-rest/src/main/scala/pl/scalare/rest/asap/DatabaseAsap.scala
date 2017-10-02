package pl.scalare.rest.asap

import java.util.Optional

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonParser
import org.fusesource.scalate.TemplateEngine
import pl.scalare.core.model.Select
import pl.scalare.impl.repo.SelectRepoFake
import pl.scalare.rest.resources.DatabaseResource
import pl.scalare.util.AppLogging

object DatabaseAsap extends AppLogging {

  val map = Map("table" -> Map("sql" -> "sql", ",header" -> List("h1", "h2"), "body" -> List(List("11", "12"), List("21", "22"))))

  b(map)

  val table = new Select(null, "sql", Array("h1", "h2"), Array(Array("11", "12"), Array("21", "22")))

  val mapper = new ObjectMapper
  val json = mapper.writeValueAsString(table)
  println(json)
  val parser = new JsonParser()
  val jsonObject = parser.parse(json).getAsJsonObject()
  val tableMap = new GsonMap(jsonObject)
  println("tableMap " + tableMap)
  val map2 = Map("table" -> tableMap)
  b(map2)

  def b(map: Map[String, AnyRef]): Unit = {
    val engine = new TemplateEngine
    println("map " + map)
    println("map " + map)
    println("map " + map)
    val output = engine.layout("/pl/scalare/rest/views/select.mustache", map)
    println(output)
  }

  def a(): Unit = {
    val repo = new SelectRepoFake
    val resource = new DatabaseResource(repo, null)
    val table = resource.select("hsql", "VIEWS", Optional.empty(), Optional.empty())
    val mapper = new ObjectMapper
    val json = mapper.writeValueAsString(table)
    println(json)
    val parser = new JsonParser()
    val jsonObject = parser.parse(json).getAsJsonObject()
    val tableMap = new GsonMap(jsonObject)
    //  println("" + tableMap)
    val map = Map("table" -> tableMap)
    println("" + map)

  }
}
