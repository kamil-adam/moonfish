package pl.scalare.domain.databases

import org.sqlite.SQLiteDataSource
import org.hsqldb.jdbc.JDBCPool

class HsqlDatabase extends Database {
  def tables = Set("TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "TABLE_TYPE", "TABLE_SOURCE")

  def url = "jdbc:hsqldb:jdbc.hsqldb"

  def ds = {
    val ds = new JDBCPool()
    ds.setDatabase(url)
    ds.setUser("SA")
    ds.setPassword("")
    ds
  }
}

object HsqlDatabase extends DatabaseApp {
  selects(new HsqlDatabase)
}