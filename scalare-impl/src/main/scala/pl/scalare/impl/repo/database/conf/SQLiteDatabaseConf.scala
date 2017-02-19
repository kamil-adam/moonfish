package pl.scalare.impl.repo.database.conf

import org.sqlite.SQLiteDataSource

class SQLiteDatabaseConf extends DatabaseConf {

  override def schema = ""

  override def keySet = Set("sqlite_master", "sqlite_temp_master")

  override def mem = "jdbc:sqlite::memory:"

  override def ds = {
    val ds = new SQLiteDataSource()
    ds.setUrl(url)
    ds
  }

  override def url = "jdbc:sqlite:" + file

  override def file = "jdbc.sqlite"

}

object SQLiteDatabaseConf extends DatabaseApp {
  run(new SQLiteDatabaseConf)
}