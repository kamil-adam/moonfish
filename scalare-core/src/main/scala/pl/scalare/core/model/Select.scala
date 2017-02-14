package pl.scalare.core.model

import scala.beans.BeanProperty

class Table (@BeanProperty var sql:String, @BeanProperty var header:Header, @BeanProperty var body:Body) {}

class Header (@BeanProperty var cells:Array[Cell]) {}

class Body (@BeanProperty var rows:Array[Row]) {}

class Row (@BeanProperty var cells:Array[Cell]) {}

class Cell (@BeanProperty var value:String, @BeanProperty var isNull:Boolean) {
  def this (any : AnyRef) = this("" + any, null == any )
}