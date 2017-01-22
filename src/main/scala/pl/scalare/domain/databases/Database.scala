package pl.scalare.domain.databases

import java.sql.DriverManager

import javax.sql.ConnectionPoolDataSource
import javax.sql.DataSource
import javax.sql.XADataSource
import org.skife.jdbi.v2.DBI

trait Database {

  def selects: Map[String, String] = tables.map(t => (t, Database.prefix + schema + t)).toMap

  def schema: String

  def tables: Set[String]

  def file: String
  def url: String
  def mem:String

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

trait DatabaseApp extends App {
  //  def selects(s : Database) = s.selects.values.foreach(v => println (v))
  def run(db: Database) = {
    db.selects.values.foreach(v => println(v))
    val ds = db.ds
    val dbi = new DBI(ds)
    db.selects.values.map(v => {
      val h = dbi.open()
      val list = h.select(v)
      h.close()
      list
    }).foreach { x => println(x) }
  }
}
  
