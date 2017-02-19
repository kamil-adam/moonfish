package pl.scalare.impl.repo.database.conf

import org.skife.jdbi.v2.DBI
import pl.scalare.util.AppLogging


trait DatabaseApp extends AppLogging {
  //  def selects(s : Database) = s.selects.values.foreach(v => println (v))
  def run(db: DatabaseConf) = {
    db.selects.values.foreach(v => logger.info(v))
    val ds = db.ds
    val dbi = new DBI(ds)

  }
}
  
