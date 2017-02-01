package pl.scalare.main

import com.google.inject.{AbstractModule, Provides}
import io.dropwizard.jdbi.DBIFactory
import pl.scalare.core.repo.{DatabaseRepo, EventRepo, SnapshotRepo}
import pl.scalare.impl.repo.{DatabaseRepoImpl, EventRepoImpl, SnapshotRepoImpl}


class ScalareModule(val configuration : ScalareConfiguration) extends AbstractModule {

  @Provides
  def provideConfiguration = configuration

  @Provides
  def provideTemplateHCConfiguration = configuration.template

  @Provides
  def provedeRepo =new DBIFactory()



  override def configure(): Unit = {
    bind(classOf[DatabaseRepo]).to(classOf[DatabaseRepoImpl]).asEagerSingleton()
    bind(classOf[EventRepo]).to(classOf[EventRepoImpl]).asEagerSingleton()
    bind(classOf[SnapshotRepo]).to(classOf[SnapshotRepoImpl]).asEagerSingleton()
  }


}
