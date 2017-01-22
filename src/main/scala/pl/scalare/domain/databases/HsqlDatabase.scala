package pl.scalare.domain.databases

import org.sqlite.SQLiteDataSource
import org.hsqldb.jdbc.JDBCPool

class HsqlDatabase extends Database {
  //  def tables = Set(
  //    "ADMINISTRABLE_ROLE_AUTHORIZATIONS",
  //    "APPLICABLE_ROLES",
  //    "ASSERTIONS",
  ////    "AUTHORIZATIONS",
  //    "CHARACTER_SETS",
  //    "CHECK_CONSTRAINTS"
  //    )

  def schema = "INFORMATION_SCHEMA."
  def tables = Set("VIEWS")

  def file = "jdbc.hsqldb"
  def url = "jdbc:hsqldb:file:" + file + ";ifexists=true"
  def mem = "jdbc:hsqldb:mem:" + file 

  def ds = {
    val ds = new JDBCPool()
    ds.setDatabase(mem)
    ds.setUser("SA")
    ds.setPassword("")
    ds
  }
}

object HsqlDatabase extends DatabaseApp {
  run(new HsqlDatabase)
}