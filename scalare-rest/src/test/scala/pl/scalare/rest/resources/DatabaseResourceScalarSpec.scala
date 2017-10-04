package pl.scalare.rest.resources

import java.util.Optional

import pl.scalare.impl.repo.SelectRepoFake
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.views.KeysView
import pl.scalare.spec.GrayScalarSpec

class DatabaseResourceScalarSpec extends GrayScalarSpec {
  describe("A DatabaseResourceScalarSpec") {
    val repo = new SelectRepoFake()
    val conf = new ViewConfiguration
    val resource = new DatabaseResource(repo, conf)
    val databases = Array("sqlite", "hsql","h2")
    it("when invoke databases") {
      resource.databases.length should be > 0
    }
    it("when invoke databasesView") {
      val view = resource.databasesView
      view.databases.length should be > 0
    }

    it("when invoke keys") {
      resource.databases.foreach {
        resource.keys(_).length should be > 0
      }
    }
    it("when invoke keysView") {
      resource.databases.foreach { key =>
          val view: KeysView = resource.keysView(key)
          view.keys.length should be > 0
      }
    }
    it("when invoke select") {
      databases.foreach { db =>
        resource.keys(db).foreach { key =>
          val select = resource.select(db, key, Optional.empty(), Optional.empty())
          select.getHeader.length should be >= 0
          select.getBody.length should be >= 0
        }
      }
    }
    it("when invoke selectView") {
      databases.foreach { db =>
        resource.keys(db).foreach { key =>
          val view = resource.selectView(db, key, Optional.empty(), Optional.empty())
          val select = view.table
          select.getHeader.length should be >= 0
          select.getBody.length should be >= 0
        }
      }
    }
    ignore("when invoke header") {
      databases.foreach { db =>
        resource.keys(db).foreach { key =>
          val header = resource.header(db, key)
          header.length should be >= 0
        }
      }
    }
  }
}
