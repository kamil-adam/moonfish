package pl.scalare.impl.repo

import pl.scalare.core.repo.SelectRepo
import pl.scalare.impl.repo.database._

class SelectRepoFake extends SelectRepo {

  val h2: DatabaseInfo = new H2Database

  val hsql: DatabaseInfo = new HsqlDatabase

  val derby: DatabaseInfo = new DerbyDatabase

  val sqlite: DatabaseInfo = new SQLiteDatabase

  private val databaseMap = Map(("h2", h2), ("hsql", hsql), ("derby", derby), ("sqlite", sqlite))

  override def databases: Iterable[String] = databaseMap.keys

  override def keys(database: String) = selects(database).keys

  private def selects(database: String) = get(database).selects

  private def get(database: String) = databaseMap.get(database).get

  override def sql(database: String, key: String) = selects(database).get(key).get

  override def query(database: String, key: String) = get(database).select(key)

}
