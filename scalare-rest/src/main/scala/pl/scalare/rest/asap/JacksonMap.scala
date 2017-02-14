package pl.scalare.rest.asap

import com.fasterxml.jackson.databind.ObjectMapper

import scala.collection.immutable.AbstractMap

class JacksonMap (val any : AnyRef) extends AbstractMap[String, Any] {
  val mapper = new ObjectMapper
  val string = mapper.writeValueAsString(any)



  override def +[B1 >: Any](kv: (String, B1)): Map[String, B1] = ???

  override def get(key: String): Option[Any] = ???

  override def iterator: Iterator[(String, Any)] = ???

  override def -(key: String): Map[String, Any] = ???
}
