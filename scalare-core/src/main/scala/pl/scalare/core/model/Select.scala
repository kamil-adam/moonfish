package pl.scalare.core.model

import scala.beans.BeanProperty

class Select (@BeanProperty var db:String, @BeanProperty var sql:String, @BeanProperty var header:Array[String], @BeanProperty var body:Array[Array[String]]) {
  def this(db : String, key:String) = this(db, key,Array(),Array())
}

class Table (@BeanProperty var sql:String, @BeanProperty var header:Header, @BeanProperty var body:Body) {}

class Header (@BeanProperty var cells:Array[Cell]) {}

class Body (@BeanProperty var rows:Array[Row]) {}

class Row (@BeanProperty var cells:Array[Cell]) {}

class Cell (@BeanProperty var v:String, @BeanProperty var isNull:Boolean) {
  def this (any : AnyRef) = this("" + any, null == any )
}