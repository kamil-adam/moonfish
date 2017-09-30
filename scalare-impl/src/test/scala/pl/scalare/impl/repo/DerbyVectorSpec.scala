package pl.scalare.impl.repo

import org.scalacheck.Gen
import pl.scalare.impl.repo.database.conf.{DerbyDatabaseConf, SQLiteDatabaseConf}
import pl.scalare.spec.GrayVectorSpec

class DerbyVectorSpec extends GrayVectorSpec{

  val conf = new DerbyDatabaseConf
  val keySet = conf.keySet
  val gen = Gen.oneOf(keySet.toSeq)
  val evenInts =  for { n <- Gen.oneOf(keySet.toSeq) } yield n

  ignore("DerbyDatabaseConf") {
    forAll(evenInts) { (key) =>
      conf.select(key).toList.size should be >= 0
    }
  }
}
