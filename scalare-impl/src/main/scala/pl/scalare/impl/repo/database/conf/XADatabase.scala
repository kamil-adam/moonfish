package pl.scalare.impl.repo.database.conf

import javax.sql.XADataSource

trait XADatabase {
  def xads: XADataSource
}
