package pl.scalare.main

import com.google.inject.{AbstractModule, Provides}
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.setup.Environment
import pl.scalare.core.client.{OmnibusProxy, OmnibusProxyImpl}
import pl.scalare.core.repo.{DatabaseRepo, EventRepo, SnapshotRepo}
import pl.scalare.impl.repo.{DatabaseRepoImpl, EventRepoImpl, SnapshotRepoImpl}
import pl.scalare.rest.ScalareResource


class ScalareModule(val c: ScalareConfiguration, val e: Environment) extends AbstractModule {

  @Provides
  def provideEnvironment = e

  @Provides
  def provideConfiguration = c

  @Provides
  def provideTemplateHCConfiguration = c.template1

  @Provides
  def provideRepo = new DBIFactory()

  @Provides
  def provideScalareResource = new ScalareResource("", c.defaultName)

  @Provides
  def proviceOmnibusProxyImpl = {
    val omnibusClient = new JerseyClientBuilder(e).using(c.omnibusClient).build("omnibusClient")
    new OmnibusProxyImpl(omnibusClient)
  }

  @Provides
  def proviceEventRepoImpl = {
    val factory = new DBIFactory()
    val sqlite = factory.build(e, c.sqlite, "sqlite")
    sqlite.onDemand(classOf[EventRepoImpl]);
  }

  override def configure(): Unit = {
    bind(classOf[DatabaseRepo]).to(classOf[DatabaseRepoImpl]).asEagerSingleton()
    bind(classOf[EventRepo]).to(classOf[EventRepoImpl]).asEagerSingleton()
    bind(classOf[SnapshotRepo]).to(classOf[SnapshotRepoImpl]).asEagerSingleton()
    bind(classOf[OmnibusProxy]).to(classOf[OmnibusProxyImpl]).asEagerSingleton()
  }


}
