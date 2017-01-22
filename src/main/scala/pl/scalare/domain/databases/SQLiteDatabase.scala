package pl.scalare.domain.databases

import org.sqlite.SQLiteDataSource

class SQLiteDatabase extends Database {

 override def schema = ""
 override def tables = Set("sqlite_temp_master")

  override def file = "jdbc.sqlite"
  override def url = "jdbc:sqlite:" + file
  override def mem = "jdbc:sqlite::memory:"

  override def ds = {
    val ds = new SQLiteDataSource()
    ds.setUrl(url)
    ds
  }

}

object SQLiteDatabase extends DatabaseApp {
  run(new SQLiteDatabase)
}