package pl.scalare.core.repo

class DatabaseRepo {
  def database  = List("h2","hsql", "derby", "sqlite")
    
  def information(database: String) = database
}