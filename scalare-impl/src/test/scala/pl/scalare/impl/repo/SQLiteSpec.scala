package pl.scalare.impl.repo

import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper
import org.sqlite.SQLiteJDBCLoader
import pl.scalare.impl.repo.database.conf.SQLiteDatabaseConf
import pl.scalare.spec.GrayScalarSpec

class SQLiteSpec extends GrayScalarSpec {
  val conf = new SQLiteDatabaseConf
  val url = conf.mem
  ignore(url) {
    val initialize = SQLiteJDBCLoader.initialize();
    val ds = conf.ds

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