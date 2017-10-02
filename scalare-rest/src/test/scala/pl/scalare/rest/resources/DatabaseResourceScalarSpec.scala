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
    val rsrc = new DatabaseResource(repo, conf)
    it("when invoke databases") {
      rsrc.databases.length should be > 0
    }
    it("when invoke databasesView") {
      val view = rsrc.databasesView
      view.databases.length should be > 0
    }

    it("when invoke keys") {
      rsrc.databases.foreach {
        rsrc.keys(_).length should be > 0
      }
    }
    it("when invoke keysView") {
      rsrc.databases.foreach { key =>
          val view: KeysView = rsrc.keysView(key)
          view.keys.length should be > 0
      }
    }
    it("when invoke select") {
      val databases = Array("sqlite", "hsql","h2")
      databases.foreach { db =>
        rsrc.keys(db).foreach { key =>
          val select = rsrc.select(db, key, Optional.empty(), Optional.empty())
          select.getHeader.length should be >= 0
          select.getBody.length should be >= 0
        }
      }
    }
  }
}
