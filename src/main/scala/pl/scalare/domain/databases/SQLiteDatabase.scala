package pl.scalare.domain.databases

import org.sqlite.SQLiteDataSource

class SQLiteDatabase extends Database {

  def tables = Set("TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "TABLE_TYPE", "TABLE_SOURCE")

  def url = "jdbc:sqlite:jdbc.sqlite"

  def ds = {
    val ds = new SQLiteDataSource();
    ds.setUrl(url);
    ds
  }

}

object SQLiteDatabase extends DatabaseApp {
  selects(new SQLiteDatabase)
}