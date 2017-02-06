package pl.scalare.impl.repo

import org.sqlite.SQLiteDataSource

class SQLiteDatabase extends DatabaseInfo {

  override def schema = ""

  override def tables = Set("sqlite_temp_master")

  override def mem = "jdbc:sqlite::memory:"

  override def ds = {
    val ds = new SQLiteDataSource()
    ds.setUrl(url)
    ds
  }

  override def url = "jdbc:sqlite:" + file

  override def file = "jdbc.sqlite"

}

object SQLiteDatabase extends DatabaseApp {
  run(new SQLiteDatabase)
}