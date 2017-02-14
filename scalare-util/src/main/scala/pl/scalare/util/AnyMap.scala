package pl.scalare.util

import scala.collection.immutable.AbstractMap

class AnyMap(val any : Any) extends AbstractMap[String, Any] {
  val clazz = any.getClass;

  override def +[B1 >: Any](kv: (String, B1)): Map[String, B1] = ???

  override def get(key: String): Option[Any] = {
    val field = clazz.getField(key)
    val value = field.get(any)
    opt(value)
  }

  override def iterator: Iterator[(String, Any)] = {
    val fields = clazz.getFields.toList
    val tuples = fields.map(f => (f.getName, f.get(any)))
    tuples.iterator
  }

  override def -(key: String): Map[String, Any] = ???


  private def opt(value: Any): Option[Any] = {
    value match {
      case a : Array[_] => Option(a.map (v => opt(v).toList))
      case a : String => Option(a)
      case a : Number => Option(a)
      case a : Boolean => Option(a)
      case a : AnyRef => Option(new AnyMap(a))
    }
  }

}
