package pl.scalare.impl.repo

import org.apache.derby.jdbc.EmbeddedDataSource
import org.scalatest.FunSpec
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper

class DerbySpec extends FunSpec {
  val url = "jdbc:h2:mem:test"
  describe(url) {
    //    val ds = new EmbeddedConnectionPoolDataSource()
    val ds = new EmbeddedDataSource();
    ds.setDatabaseName(url);

    ds.setCreateDatabase("create");

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