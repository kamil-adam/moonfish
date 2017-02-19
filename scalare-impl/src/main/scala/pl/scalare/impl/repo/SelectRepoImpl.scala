package pl.scalare.impl.repo

import pl.scalare.core.model.OptData
import pl.scalare.core.repo.SelectRepo

class SelectRepoImpl extends SelectRepo {
  override def databases: Iterable[String] = ???

  override def keys(database: String): Iterable[String] = ???

  override def sql(database: String, key: String): String = ???

  override def select(database: String, key: String, optData: OptData): Iterable[Map[String, AnyRef]] = ???
}
