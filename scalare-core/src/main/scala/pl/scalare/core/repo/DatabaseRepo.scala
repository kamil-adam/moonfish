package pl.scalare.core.repo

import pl.scalare.core.model.Database

trait DatabaseRepo {

  def databases:Array[Database]

  def information(database: String) = Iterable(database)
}