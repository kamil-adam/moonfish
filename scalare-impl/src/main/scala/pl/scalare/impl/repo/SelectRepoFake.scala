package pl.scalare.impl.repo

import pl.scalare.core.model.OptData
import pl.scalare.core.repo.SelectRepo
import pl.scalare.impl.repo.database.conf._

class SelectRepoFake extends SelectRepo {

  val h2: DatabaseConf = new H2DatabaseConf

  val hsql: DatabaseConf = new HsqlDatabaseConf

  val derby: DatabaseConf = new DerbyDatabaseConf

  val sqlite: DatabaseConf = new SQLiteDatabaseConf

  private val databaseMap = Map(("h2", h2), ("hsql", hsql), ("derby", derby), ("sqlite", sqlite))

  override def databases: Iterable[String] = databaseMap.keys.toList.sorted

  override def keys(database: String) = get(database).keys

  private def get(database: String) = databaseMap.get(database).get

  override def sql(database: String, key: String) = selects(database).get(key).get

  private def selects(database: String) = get(database).selects

  override def select(database: String, key: String, optData: OptData) = get(database).select(key)

}
