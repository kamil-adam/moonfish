package pl.scalare.impl.repo.database.conf

import javax.sql.DataSource

import org.skife.jdbi.v2.DBI

import scala.collection.JavaConverters._
import scala.util.control.NonFatal

trait DatabaseConf {

  def schema: String

  def keySet: Set[String]

  def file: String

  def url: String

  def mem: String

  def ds: DataSource

  def select(key: String) = {
    try {
      val sql = selects.get(key.toLowerCase).get
      val dbi = new DBI(ds)
      val h = dbi.open()
      val list = h.select(sql)
      h.close()
      list.asScala.map(a => a.asScala.toMap)
    } catch {
      case NonFatal(e) => throw new IllegalArgumentException("[" + key + "]" + selects, e)
    }
  }

  def selects: Map[String, String] = keys.map(t => (t, DatabaseConf.prefix + schema + t)).toMap

  def keys: List[String] = keySet.map(k => k.toLowerCase).toList.sorted

}

object DatabaseConf {
  def prefix = "SELECT * FROM "

  def folder = "database/"

}