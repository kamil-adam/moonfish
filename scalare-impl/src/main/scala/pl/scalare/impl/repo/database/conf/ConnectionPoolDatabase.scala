package pl.scalare.impl.repo.database.conf

import javax.sql.ConnectionPoolDataSource

trait ConnectionPoolDatabase {
  def cpds: ConnectionPoolDataSource
}
