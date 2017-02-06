package pl.scalare.impl.repo

import pl.scalare.core.model.Database
import pl.scalare.core.repo.DatabaseRepo

class DatabaseRepoImpl extends DatabaseRepo {

  val h2: DatabaseInfo = new H2Database

  val hsql: DatabaseInfo = new HsqlDatabase

  val derby: DatabaseInfo = new DerbyDatabase

  val sqlite: DatabaseInfo = new SQLiteDatabase

  val map = Map(("h2", h2), ("hsql", hsql), ("derby", derby), ("sqlite", sqlite))

  override def databases = Array(new Database("h2"), new Database("hsql"), new Database("derby"),new Database("sqlite"))

  override def information(database: String) = map.get(database).get.tables
}