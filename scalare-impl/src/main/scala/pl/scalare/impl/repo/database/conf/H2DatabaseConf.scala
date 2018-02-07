package pl.scalare.impl.repo.database.conf

import org.h2.jdbcx.JdbcConnectionPool

class H2DatabaseConf extends DatabaseConf {

  override def schema = "INFORMATION_SCHEMA."

  override def keySet = Set(
    "CATALOGS",
    "COLLATIONS",
    "COLUMNS",
    "COLUMN_PRIVILEGES",
    "CONSTANTS",
    "CONSTRAINTS",
    "CROSS_REFERENCES",
    "DOMAINS",
    "FUNCTION_ALIASES",
    "FUNCTION_COLUMNS",
    "HELP",
    "INDEXES",
    "IN_DOUBT",
    "LOCKS",
    "QUERY_STATISTICS",
    "RIGHTS",
    "ROLES",
    "SCHEMATA",
    "SEQUENCES",
    "SESSIONS",
    "SESSION_STATE",
    "SETTINGS",
    "TABLES",
    "TABLE_PRIVILEGES",
    "TABLE_TYPES",
    "TRIGGERS",
    "TYPE_INFO",
    "USERS",
    "VIEWS")

  override def mem = "jdbc:h2:mem:" + file

  def tcp = "jdbc:h2:tsc:" + file

  override def ds = JdbcConnectionPool.create(url, "username", "password");

  override def url = "jdbc:h2:./" + file

  override def file = "jdbc.h2"

  def cpds = JdbcConnectionPool.create(url, "username", "password");

}

object H2DatabaseConf extends DatabaseApp {
  run(new H2DatabaseConf)
}