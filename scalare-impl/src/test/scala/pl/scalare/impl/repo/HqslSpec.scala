package pl.scalare.impl.repo

import com.typesafe.scalalogging.LazyLogging
import org.hsqldb.jdbc.JDBCPool
import org.scalatest.FunSpec
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.util.StringMapper
import pl.scalare.impl.repo.database.conf.{H2DatabaseConf, HsqlDatabaseConf}
import pl.scalare.util.AppLogging

class HqslSpec extends FunSpec with LazyLogging {
  val conf = new HsqlDatabaseConf
  val url = conf.mem
  describe(url) {
    val ds = conf.ds

    describe("select") {
      it("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val list = h.select("select * from INFORMATION_SCHEMA.SCHEMATA")

        val catalogName = list.get(0).get("CATALOG_NAME")
        logger.info(list.toString)
        h.close();
      }
    }
    describe("createQuery") {
      it("COLUMNS") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select TABLE_NAME from INFORMATION_SCHEMA.COLUMNS")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }

      it("TABLES") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select TABLE_NAME from INFORMATION_SCHEMA.TABLES")

        val mapped = query.map(StringMapper.FIRST);
        val list = mapped.list();

        h.close();
      }
    }
  }
}