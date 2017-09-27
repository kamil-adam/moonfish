package pl.scalare.impl.repo

import org.scalatest.FunSpec
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper
import org.sqlite.{SQLiteDataSource, SQLiteJDBCLoader}

class SQLiteSpec extends FunSpec {
  val url = "jdbc:sqlite:jdbc.sqlite"
  describe(url) {
    val initialize = SQLiteJDBCLoader.initialize();
    val ds = new SQLiteDataSource();
    ds.setUrl(url);

    describe("when empty") {
      ignore("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val list = h.select("select * from INFORMATION_SCHEMA.SCHEMATA")

        val catalogName = list.get(0).get("CATALOG_NAME")

        h.close();
      }
    }
    describe("when empty") {
      ignore("CATALOGS") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select CATALOG_NAME from INFORMATION_SCHEMA.CATALOGS")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }
      ignore("TABLE_TYPES") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select TYPE from INFORMATION_SCHEMA.TABLE_TYPES")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }
    }
  }
}