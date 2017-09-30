package pl.scalare.impl.repo

import org.scalacheck.Gen
import pl.scalare.impl.repo.database.conf.{H2DatabaseConf, HsqlDatabaseConf}
import pl.scalare.spec.GrayVectorSpec

class HsqlVectorSpec extends GrayVectorSpec{

  val conf = new HsqlDatabaseConf
  val keySet = conf.keySet
  val gen = Gen.oneOf(keySet.toSeq)
  val evenInts =  for { n <- Gen.oneOf(keySet.toSeq) } yield n

  ignore("HsqlDatabaseConf") {
    forAll(evenInts) { (key) =>
      conf.select(key).toList.size should be >= 0
    }
  }
}
