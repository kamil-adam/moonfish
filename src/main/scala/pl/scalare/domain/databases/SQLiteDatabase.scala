package pl.scalare.domain.databases

import org.sqlite.SQLiteDataSource

class SQLiteDatabase extends Database {

  def schema = ""
  def tables = Set("sqlite_temp_master")

  def file = "jdbc.sqlite"
  def url = "jdbc:sqlite:" + file
  def mem = "jdbc:sqlite::memory:"

  def ds = {
    val ds = new SQLiteDataSource()
    ds.setUrl(url)
    ds
  }

}

object SQLiteDatabase extends DatabaseApp {
  run(new SQLiteDatabase)
}