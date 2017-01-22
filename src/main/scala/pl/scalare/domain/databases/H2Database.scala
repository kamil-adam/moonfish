package pl.scalare.domain.databases

import org.h2.jdbcx.JdbcConnectionPool
import org.h2.Driver

class H2Database extends Database {

  def schema = "INFORMATION_SCHEMA."
  
  def tables = Set(
      "CATALOGS", 
      "COLLATIONS", 
      "COLUMNS", 
      "COLUMN_PRIVILEGES", 
      "CONSTANTS")

  def file = "jdbc.h2"
  def url = "jdbc:h2::./" + file
  def mem = "jdbc:h2:mem:" + file
  def tcp = "jdbc:h2:tsp:" + file

  def ds = JdbcConnectionPool.create(url, "username", "password");

  def cpds = JdbcConnectionPool.create(url, "username", "password");

}

object H2Database extends DatabaseApp {
  run(new H2Database)
}