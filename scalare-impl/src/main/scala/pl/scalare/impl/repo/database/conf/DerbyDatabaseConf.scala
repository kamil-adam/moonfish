package pl.scalare.impl.repo.database.conf

import javax.sql.DataSource

import org.apache.derby.jdbc.EmbeddedDataSource

class DerbyDatabaseConf extends DatabaseConf {
  override def schema: String = ""

  override def keySet: Set[String] = Set(
    "SYSALIASES",
    "SYSCHECKS",
    "SYSCOLPERMS",
    "SYSCOLUMNS",
    "SYSCONGLOMERATES",
    "SYSCONSTRAINTS",
    "SYSDEPENDS",
    "SYSFILES",
    "SYSFOREIGNKEYS",
    "SYSKEYS",
    "SYSPERMS",
    "SYSROLES",
    "SYSROUTINEPERMS",
    "SYSSCHEMAS",
    "SYSSEQUENCES",
    "SYSSTATEMENTS",
    "SYSSTATISTICS",
    "SYSTABLEPERMS",
    "SYSTABLES",
    "SYSTRIGGERS",
    "SYSUSERS",
    "SYSVIEWS"
  )

  override def url: String = "jdbc:derby:" + file + ";create=true"

  override def file: String = "jdbc.derby"

  override def ds: DataSource = {
    val ds = new EmbeddedDataSource();
    ds.setDatabaseName(mem);
    //ds.setCreateDatabase("create");
    return ds;
  }

  override def mem: String = "jdbc:derby:memory:" + file + ";create=true"
}
