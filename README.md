* https://codecov.io/gh/writeonly/scalare
* https://circleci.com/gh/writeonly/scalare

# ScalaRE

FOR people
WHO want to make a query
THE Scalare IS A database selector and manager
THAT is a one jar
UNLIKE others database manager
OUR PRODUCT is a one jar

# Main technology
* Scala for logic
* Dropwizard for Rest
* Guice for DI
* Mustache for view

# Other technology
* ScalaTest
* ScalaMock
* ScalaTE
* Scalatra

# Database
* Embedded SQL
 * derby
 * h2
 * hsql
 * sqlite
* Remote SQL
 * CUBRID
 * Firebird
 * MariaDB
 * MySQL
 * PostgreSQL
* Like-SQL NoSql
 * apache ignite
 * cassandra
 * couchbase
* Other NoSql
 * Redis



java -jar scalare-main/target/scalare-main-1.0.6-SNAPSHOT.jar server scalare.yml

java -cp scalare-main/target/scalare-main-1.0.6-SNAPSHOT.jar pl.scalare.rest.asaps.DatabaseAsap

mvn clean install; java -jar scalare-main/target/scalare-main-1.0.6-SNAPSHOT.jar server scalare.yml

mvn clean install; java -cp scalare-main/target/scalare-main-1.0.6-SNAPSHOT.jar pl.scalare.rest.asaps.DatabaseAsap
