package pl.scalare.core.model

import java.util.Optional

case class OptData(val limit: Option[Int], val offset: Option[Int]) {

}

object OptDatas {
  implicit def optInt(o : Optional[Integer]) : Option[Int] = Option(o.orElse(null))
    .map(i => i.intValue())
    .orElse(Option.empty)
  implicit def optString(o : Optional[String]) : Option[String] = Option(o.orElse(null))
}
