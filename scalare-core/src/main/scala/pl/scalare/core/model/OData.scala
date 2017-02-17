package pl.scalare.core.model


class OData (val orderby :String, val top : Option[Int], val filter:String, val format : Boolean, val select: List[String])  {

}
