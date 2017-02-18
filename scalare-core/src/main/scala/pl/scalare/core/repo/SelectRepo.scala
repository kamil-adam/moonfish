package pl.scalare.core.repo

import pl.scalare.core.model.OptData

trait SelectRepo {

  def databases: Iterable[String]

  def keys(database: String): Iterable[String]

  def sql(database: String, key: String): String

  def select(database: String, key: String, optData : OptData): Iterable[Map[String, AnyRef]]
}