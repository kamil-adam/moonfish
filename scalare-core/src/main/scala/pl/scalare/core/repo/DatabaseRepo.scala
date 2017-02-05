package pl.scalare.core.repo

trait DatabaseRepo {

  def databases:Array[Map[String,String]]

  def information(database: String) = Iterable(database)
}