package pl.scalare.domain.databases

import org.h2.jdbcx.JdbcConnectionPool
import org.h2.Driver

class H2Database extends Database {

  def tables = Set("TABLE_CATALOG", "TABLE_SCHEMA", "TABLE_NAME", "TABLE_TYPE", "TABLE_SOURCE")
  
  def url = "jdbc:h2::jdbc"
  def mem = "jdbc:h2:mem:jdbc.h2"
  def tcp = "jdbc:h2:tsp:jdbc.h2"
  
  def ds = JdbcConnectionPool.create(url,"username","password");
  
  def cpds =  JdbcConnectionPool.create(url,"username","password");

}