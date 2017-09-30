package pl.scalare.impl.repo

import com.typesafe.scalalogging.LazyLogging
import org.scalacheck.Gen
import pl.scalare.impl.repo.database.conf.{DerbyDatabaseConf, H2DatabaseConf}
import pl.scalare.spec.GrayVectorSpec

class H2VectorSpec extends GrayVectorSpec with LazyLogging {

  val conf = new H2DatabaseConf
  val keySet = conf.keySet
  val gen = Gen.oneOf(keySet.toSeq)
//  val evenInts =  for { n <- Gen.oneOf(keySet.toSeq) } yield n
  val evenInts =   Gen.oneOf(keySet.toSeq)

  logger.info(keySet.mkString)
  logger.info(keySet.toString)
  logger.info(evenInts.toString)

  ignore("H2DatabaseConf") {
    keySet.foreach{key =>
      logger.info("key =>" + key)
      conf.select(key).toList.size should be >= 0
    }
  }
}
