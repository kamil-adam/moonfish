package pl.scalare.impl.repo

import org.scalatest.FunSpec
import org.h2.jdbcx.JdbcConnectionPool

import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper

class H2Spec extends FunSpec {
  val url = "jdbc:h2:mem:test"
  describe(url) {
    val ds = JdbcConnectionPool.create(url,"username","password");

    describe("select") {
      it("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val list = h.select("select * from INFORMATION_SCHEMA.SCHEMATA")

        val catalogName = list.get(0).get("CATALOG_NAME")

        h.close();
      }
      it("CATALOGS") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select CATALOG_NAME from INFORMATION_SCHEMA.CATALOGS")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }
    }
    describe("createQueryt") {
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