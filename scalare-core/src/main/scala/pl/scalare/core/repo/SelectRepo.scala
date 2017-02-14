package pl.scalare.core.repo

trait SelectRepo {

  def databases: Iterable[String]

  def keys(database: String): Iterable[String]

  def sql(database: String, key: String): String

  def select(database: String, key: String): Iterable[Map[String, AnyRef]]
}