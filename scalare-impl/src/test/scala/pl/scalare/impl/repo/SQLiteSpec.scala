package pl.scalare.impl.repo

import org.scalatest.FunSpec
import org.h2.jdbcx.JdbcConnectionPool

import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper
import java.sql.DriverManager
import java.io.IOException
import java.sql.Connection
import org.sqlite.SQLiteJDBCLoader
import org.sqlite.SQLiteDataSource

class SQLiteSpec extends FunSpec {
  val url = "jdbc:sqlite:jdbc.sqlite"
  describe(url) {
    val initialize = SQLiteJDBCLoader.initialize();
    val ds = new SQLiteDataSource();
    ds.setUrl(url);

    describe("when empty") {
      it("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val list = h.select("select * from INFORMATION_SCHEMA.SCHEMATA")

        val catalogName = list.get(0).get("CATALOG_NAME")

        h.close();
      }
    }
    describe("when empty") {
      it("CATALOGS") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select CATALOG_NAME from INFORMATION_SCHEMA.CATALOGS")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }
      it("TABLE_TYPES") {
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