package pl.scalare.core.repo

trait DatabaseRepo {

  def database = List("h2", "hsql", "derby", "sqlite")

  def information(database: String) = Iterable(database)
}