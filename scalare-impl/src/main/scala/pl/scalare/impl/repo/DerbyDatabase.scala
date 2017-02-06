package pl.scalare.impl.repo

import javax.sql.DataSource

class DerbyDatabase extends DatabaseInfo {
  override def schema: String = ???

  override def tables: Set[String] = ???

  override def file: String = ???

  override def url: String = ???

  override def mem: String = ???

  override def ds: DataSource = ???
}
