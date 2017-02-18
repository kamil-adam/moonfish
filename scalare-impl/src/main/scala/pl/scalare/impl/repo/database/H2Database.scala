package pl.scalare.impl.repo.database

import org.h2.jdbcx.JdbcConnectionPool

class H2Database extends DatabaseInfo {

  override def schema = "INFORMATION_SCHEMA."

  override def keys = Set(
    "CATALOGS",
    "COLLATIONS",
    "COLUMNS",
    "COLUMN_PRIVILEGES",
    "CONSTANTS").map(k => k.toLowerCase)

  override def mem = "jdbc:h2:mem:" + file

  def tcp = "jdbc:h2:tsc:" + file

  override def ds = JdbcConnectionPool.create(url, "username", "password");

  def cpds = JdbcConnectionPool.create(url, "username", "password");

  override def url = "jdbc:h2:./" + file

  override def file = "jdbc.h2"

}

object H2Database extends DatabaseApp {
  run(new H2Database)
}