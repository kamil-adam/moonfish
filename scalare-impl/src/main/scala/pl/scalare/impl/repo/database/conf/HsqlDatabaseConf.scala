package pl.scalare.impl.repo.database.conf

import org.hsqldb.jdbc.JDBCPool

class HsqlDatabaseConf extends DatabaseConf {
  def tables = Set()

  override def schema = "INFORMATION_SCHEMA."

  override def keySet = Set(
    "ADMINISTRABLE_ROLE_AUTHORIZATIONS",
    "APPLICABLE_ROLES",
    "ASSERTIONS",
    "AUTHORIZATIONS",
    "CHARACTER_SETS",
    "CHECK_CONSTRAINTS",
    "CHECK_CONSTRAINT_ROUTINE_USAGE",
    "COLLATIONS",
    "COLUMNS",
    "COLUMN_COLUMN_USAGE",
    "COLUMN_DOMAIN_USAGE",
    "COLUMN_PRIVILEGES",
    "COLUMN_UDT_USAGE",
    "CONSTRAINT_COLUMN_USAGE",
    "CONSTRAINT_TABLE_USAGE",
    "DATA_TYPE_PRIVILEGES",
    "DOMAINS",
    "DOMAIN_CONSTRAINTS",
    "ELEMENT_TYPES",
    "ENABLED_ROLES",
    "INFORMATION_SCHEMA_CATALOG_NAME",
    "KEY_COLUMN_USAGE",
    "PARAMETERS",
    "REFERENTIAL_CONSTRAINTS",
    "ROLE_AUTHORIZATION_DESCRIPTORS",
    "ROLE_COLUMN_GRANTS",
    "ROLE_ROUTINE_GRANTS",
    "ROLE_TABLE_GRANTS",
    "ROLE_UDT_GRANTS",
    "ROLE_USAGE_GRANTS",
    "ROUTINE_COLUMN_USAGE",
    "ROUTINE_JAR_USAGE",
    "ROUTINE_PRIVILEGES",
    "ROUTINE_ROUTINE_USAGE",
    "ROUTINE_SEQUENCE_USAGE",
    "ROUTINE_TABLE_USAGE",
    "ROUTINES",
    "SCHEMATA",
    "SEQUENCES",
    "SQL_FEATURES",
    "SQL_IMPLEMENTATION_INFO",
    "SQL_PACKAGES",
    "SQL_PARTS",
    "SQL_SIZING",
    "SQL_SIZING_PROFILES",
    "TABLES",
    "TABLE_CONSTRAINTS",
    "TABLE_PRIVILEGES",
    "TRANSLATIONS",
    "TRIGGERED_UPDATE_COLUMNS",
    "TRIGGERS",
    "TRIGGER_COLUMN_USAGE",
    "TRIGGER_ROUTINE_USAGE",
    "TRIGGER_SEQUENCE_USAGE",
    "TRIGGER_TABLE_USAGE",
    "USAGE_PRIVILEGES",
    "USER_DEFINED_TYPES",
    "VIEWS",
    "VIEW_COLUMN_USAGE",
    "VIEW_ROUTINE_USAGE",
    "VIEW_TABLE_USAGE")

  override def url = "jdbc:hsqldb:file:" + file + ";ifexists=true"

  override def ds = {
    val ds = new JDBCPool()
    ds.setDatabase(mem)
    ds.setUser("SA")
    ds.setPassword("")
    ds
  }

  def mem = "jdbc:hsqldb:mem:" + file

  override def file = "jdbc.hsqldb"
}

object HsqlDatabaseConf extends DatabaseApp {
  run(new HsqlDatabaseConf)
}