package pl.scalare.infrastructure

import org.scalatest.FunSpec
import org.h2.jdbcx.JdbcConnectionPool

import org.skife.jdbi.v2.DBI



class H2Spec extends FunSpec {
  describe("A Set") {
    describe("when empty") {

      val ds = JdbcConnectionPool.create("jdbc:h2:mem:test",
        "username",
        "password");

      it("should have size 0") {
        assert(Set.empty.size == 0)
      }

      it("should produce NoSuchElementException when head is invoked") {
        assertThrows[NoSuchElementException] {
          Set.empty.head
        }
      }
     it("SCHEMATA") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val query = h.createQuery("select * from INFORMATION_SCHEMA.SCHEMATA")
//          .map(StringMapper.FIRST)

//        assertThat(name, equalTo("Brian"));

        h.close();
      }
      it("CATALOGS") {
        val dbi = new DBI(ds);
        val h = dbi.open();

        val name = h.createQuery("select CATALOG_NAME from INFORMATION_SCHEMA.CATALOGS")
//          .map(StringMapper.FIRST)

//        assertThat(name, equalTo("Brian"));

        h.close();
      }
    }
  }
}