package pl.scalare.rest.asap


import com.google.gson._

import scala.collection.JavaConverters._
import scala.collection.immutable.AbstractMap

class GsonMap(val json: JsonObject) extends AbstractMap[String, Any] {
  override def +[B1 >: Any](kv: (String, B1)): Map[String, B1] = ???

  override def get(key: String): Option[Any] = Option(map(json.get(key)))

  override def iterator: Iterator[(String, Any)] = json
    .entrySet
    .asScala
    .map(e => (e.getKey, map(e.getValue)))
    .iterator

  override def -(key: String): Map[String, Any] = ???

  private def map0(element: JsonElement): Any = element match {
    case j: JsonNull => ""
    case j: JsonObject => new GsonMap(j)
    case j: JsonPrimitive => j.getAsString
    case j: JsonArray => j.iterator().asScala.map(e => map(e)).toList
  }

  private def map(element: JsonElement): Any = {
    if (element == null)
      return "";

    element match {
      case j: JsonNull => ""
      case j: JsonObject => new GsonMap(j)
      case j: JsonPrimitive => j.getAsString
      case j: JsonArray => j.iterator().asScala.map(e => map(e)).toList
    }

  }
}
