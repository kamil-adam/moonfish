package pl.scalare.infrastructure

import org.scalatest.FunSpec

import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper

import org.hsqldb.jdbc.JDBCPool

class HqslSpec extends FunSpec {
  describe("jdbc:hsqldb:file:testdb") {
    val ds = new JDBCPool()
    ds.setDatabase("jdbc:hsqldb:file:testdb")
    ds.setUser("SA")
    ds.setPassword("")

    describe("select") {
      it("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val list = h.select("select * from INFORMATION_SCHEMA.SCHEMATA")

        val catalogName = list.get(0).get("CATALOG_NAME")

        h.close();
      }
    }
    describe("createQuery") {
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