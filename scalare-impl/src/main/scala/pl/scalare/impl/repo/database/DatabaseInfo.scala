package pl.scalare.impl.repo.database

import javax.sql.{ConnectionPoolDataSource, DataSource, XADataSource}

import org.skife.jdbi.v2.DBI
import pl.scalare.util.AppLogging

import scala.collection.JavaConverters.{asScalaBufferConverter, mapAsScalaMapConverter}

trait DatabaseInfo {

  def schema: String

  def tables: Set[String]

  def file: String

  def url: String

  def mem: String

  def ds: DataSource

  def select(key: String) = {
    val sql = selects.get(key).get
    val dbi = new DBI(ds)
    val h = dbi.open()
    val list = h.select(sql)
    h.close()
    list.asScala.map(a => a.asScala.toMap).toIterable

  }

  def selects: Map[String, String] = tables.map(t => (t, DatabaseInfo.prefix + schema + t)).toMap

}

trait ConnectionPoolDatabase {
  def cpds: ConnectionPoolDataSource
}

trait XADatabase {
  def xads: XADataSource
}

object DatabaseInfo {
  def prefix = "SELECT * FROM "

  def folder = "database/"


}

trait DatabaseApp extends AppLogging {
  //  def selects(s : Database) = s.selects.values.foreach(v => println (v))
  def run(db: DatabaseInfo) = {
    db.selects.values.foreach(v => logger.info(v))
    val ds = db.ds
    val dbi = new DBI(ds)

  }
}
  
