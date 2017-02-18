package pl.scalare.impl.repo.database

import org.hsqldb.jdbc.JDBCPool

class HsqlDatabase extends DatabaseInfo {
  //  def tables = Set(
  //    "ADMINISTRABLE_ROLE_AUTHORIZATIONS",
  //    "APPLICABLE_ROLES",
  //    "ASSERTIONS",
  ////    "AUTHORIZATIONS",
  //    "CHARACTER_SETS",
  //    "CHECK_CONSTRAINTS"
  //    )

  override def schema = "INFORMATION_SCHEMA."

  override def keys = Set("VIEWS").map(k=> k.toLowerCase)

  override def url = "jdbc:hsqldb:file:" + file + ";ifexists=true"

  override def ds = {
    val ds = new JDBCPool()
    ds.setDatabase(mem)
    ds.setUser("SA")
    ds.setPassword("")
    ds
  }

  def mem = "jdbc:hsqldb:mem:" + file

  override def file = "jdbc.hsqldb"
}

object HsqlDatabase extends DatabaseApp {
  run(new HsqlDatabase)
}