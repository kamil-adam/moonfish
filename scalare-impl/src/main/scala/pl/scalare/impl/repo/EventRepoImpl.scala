package pl.scalare.impl.repo

import java.sql.ResultSet

import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper
import org.skife.jdbi.v2.sqlobject.{Bind, BindBean, SqlQuery, SqlUpdate}
import org.skife.jdbi.v2.tweak.ResultSetMapper
import pl.scalare.core.model.Event
import pl.scalare.core.repo.EventRepo

@RegisterMapper(Array(classOf[EventMapper]))
trait EventRepoImpl extends EventRepo {
  @SqlUpdate("create table events (id int primary key, uuid varchar, json varchar)")
  def createSomethingTable()

  @SqlUpdate("insert into events (id, uuid, json) values (:id, :uuid, :json)")
  def insert(@BindBean event: Event)

  @SqlUpdate("insert into events (id, uuid, json) values (:id, :uuid, :json)")
  def insert(@Bind("id") id: Int, @Bind("uuid") uuid: String, @Bind("json") json: String)

  @SqlQuery("select * from events")
  def getAll: List[Event]

  @SqlQuery("select * from events where id = :id")
  def findById(@Bind("id") id: Int): Event
}

class EventMapper extends ResultSetMapper[Event] {
  def map(index: Int, rs: ResultSet, ctx: StatementContext) = new Event(rs.getInt("id"), rs.getString("uuid"), rs.getString("json"))
}
