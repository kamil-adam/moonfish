package pl.scalare.main.healthchecks

import javax.inject.Inject

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result
import pl.scalare.impl.repo.database.conf.SQLiteDatabaseConf

class EventHealthCkeck @Inject()(val database: SQLiteDatabaseConf) extends HealthCheck {

  @Override
  def check(): Result = {
    //      return if (database.isConnected()) {
    //         Result.healthy()
    //      } else {
    //         Result.unhealthy("Cannot connect to " + database.getUrl());
    //      }
    return Result.healthy()
  }
}
