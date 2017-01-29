package pl.scalare.impl.repo.data

import javax.sql.{ConnectionPoolDataSource, DataSource, XADataSource}

import org.skife.jdbi.v2.DBI
import pl.scalare.util.AppLogging

trait Database {

  def selects: Map[String, String] = tables.map(t => (t, Database.prefix + schema + t)).toMap

  def schema: String

  def tables: Set[String]

  def file: String

  def url: String

  def mem: String

  def ds: DataSource

}

trait ConnectionPoolDatabase {
  def cpds: ConnectionPoolDataSource
}

trait XADatabase {
  def xads: XADataSource
}

object Database {
  def prefix = "SELECT * FROM "

  def folder = "database/"
}

trait DatabaseApp extends AppLogging {
  //  def selects(s : Database) = s.selects.values.foreach(v => println (v))
  def run(db: Database) = {
    db.selects.values.foreach(v => logger.info(v))
    val ds = db.ds
    val dbi = new DBI(ds)
    db.selects.values.map(v => {
      val h = dbi.open()
      val list = h.select(v)
      h.close()
      list
    }).foreach { x => logger.info("" + x) }
  }
}
  
