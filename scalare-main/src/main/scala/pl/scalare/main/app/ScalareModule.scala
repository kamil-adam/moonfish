package pl.scalare.main.app

import com.google.common.collect.Lists
import com.google.inject.{ AbstractModule, Provides }
import io.dropwizard.auth.basic.BasicCredentialAuthFilter
import io.dropwizard.auth.chained.ChainedAuthFilter
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter
import io.dropwizard.auth.{ AuthDynamicFeature, AuthValueFactoryProvider }
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.jdbi.DBIFactory
import io.dropwizard.server.AbstractServerFactory
import io.dropwizard.setup.Environment
import pl.scalare.core.client.{ OmnibusProxy, OmnibusProxyImpl }
import pl.scalare.core.repo._
import pl.scalare.impl.repo._
import pl.scalare.rest.User

class ScalareModule(val c: ScalareConfiguration, val e: Environment) extends AbstractModule {

  @Provides
  def provideEnvironment = e

  @Provides
  def provideConfiguration = c

  @Provides
  def provideServerConfiguration = c.getServerFactory

  @Provides
  def provideLoggingConfiguration = c.getLoggingFactory

  @Provides
  def provideMetricsConfiguration = c.getMetricsFactory

  @Provides
  def provideViewConfiguration = {
    val server = c.getServerFactory.asInstanceOf[AbstractServerFactory]
    val view = c.getView
    view
  }

  @Provides
  def provideRepo = new DBIFactory()

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

  def proviceHCRepoImpl = new HealthCheckRepoImpl(e.healthChecks)

  @Provides
  def proviceAuthDynamicFeature = {
    //    e.register(new AuthDynamicFeature(
    //      new BasicCredentialAuthFilter.Builder[User]()
    //        .setAuthenticator(new ScalareAuthenticator())
    //        .setAuthorizer(new ScalareAuthorizer())
    //        .setRealm("SUPER SECRET STUFF")
    //        .buildAuthFilter()))

    val basicCredentialAuthFilter = new BasicCredentialAuthFilter.Builder()
      .setAuthenticator(new BasicAuthenticator())
      .setAuthorizer(new ScalareAuthorizer())
      .setPrefix("Basic")
      .buildAuthFilter()

    val oauthCredentialAuthFilter = new OAuthCredentialAuthFilter.Builder()
      .setAuthenticator(new OAuthAuthenticator())
      .setAuthorizer(new ScalareAuthorizer())
      .setPrefix("Bearer")
      .buildAuthFilter()

    val filter = new ChainedAuthFilter(Lists.newArrayList(basicCredentialAuthFilter, oauthCredentialAuthFilter))
    new AuthDynamicFeature(filter)
  }

  @Provides
  def proviceBinder = new AuthValueFactoryProvider.Binder(classOf[User])

  override def configure(): Unit = {
    bind(classOf[SelectRepo]).to(classOf[SelectRepoFake]).asEagerSingleton()
    bind(classOf[TaskRepo]).to(classOf[TaskRepoFake]).asEagerSingleton()
    bind(classOf[HealthCheckRepo]).to(classOf[HealthCheckRepoImpl]).asEagerSingleton()
    bind(classOf[EventRepo]).to(classOf[EventRepoImpl]).asEagerSingleton()
    bind(classOf[OmnibusProxy]).to(classOf[OmnibusProxyImpl]).asEagerSingleton()
  }

}
