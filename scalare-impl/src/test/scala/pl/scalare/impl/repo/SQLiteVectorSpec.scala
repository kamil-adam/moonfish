package pl.scalare.impl.repo

import org.scalacheck.Gen
import pl.scalare.impl.repo.database.conf.SQLiteDatabaseConf
import pl.scalare.spec.GrayVectorSpec

class SQLiteVectorSpec extends GrayVectorSpec{

  val conf = new SQLiteDatabaseConf
  val keySet = conf.keySet
  val gen = Gen.oneOf(keySet.toSeq)
  val evenInts =  for { n <- Gen.oneOf(keySet.toSeq) } yield n

  property("SQLiteDatabaseConf") {
    forAll(evenInts) { (key) =>
      conf.select(key).toList.size should be >= 0
    }
  }
}
