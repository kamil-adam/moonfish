package pl.scalare.impl.repo

import pl.scalare.core.repo.SelectRepo

class SelectRepoImpl extends SelectRepo {
  override def databases: Iterable[String] = ???

  override def keys(database: String): Iterable[String] = ???

  override def sql(database: String, key: String): String = ???

  override def query(database: String, key: String): Iterable[Map[String, AnyRef]] = ???
}
