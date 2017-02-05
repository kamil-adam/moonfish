package pl.scalare.impl.repo

import pl.scalare.core.repo.DatabaseRepo

class DatabaseRepoImpl extends DatabaseRepo {

  val h2: Database = new H2Database

  val hsql: Database = new HsqlDatabase

  val derby: Database = new DerbyDatabase

  val sqlite: Database = new SQLiteDatabase

  val map = Map(("h2", h2), ("hsql", hsql), ("derby", derby), ("sqlite", sqlite))

  override def database = List("h2", "hsql", "derby", "sqlite")

  override def information(database: String) = map.get(database).get.tables
}