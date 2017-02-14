package pl.scalare.rest.resources

import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, PathParam, Produces}

import com.codahale.metrics.annotation.Timed
import pl.scalare.core.model._
import pl.scalare.core.repo.SelectRepo
import pl.scalare.rest.views.{DatabasesView, SelectView}

@Path("/database")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class DatabaseResource @Inject()(@Inject val repo: SelectRepo) {

  @GET
  @Path("/view")
  @Timed
  def databasesView = new DatabasesView(databases.map(name => new Database(name)).toArray)

  @GET
  @Path("/")
  @Timed
  def databases = repo.databases.toArray

  @GET
  @Path("/{db}/key")
  @Timed
  def keys(@PathParam("db") db: String) = repo.keys(db).toArray

  @GET
  @Path("/{db}/key/view")
  @Timed
  def keysView(@PathParam("db") db: String) = repo.keys(db).toArray


  @GET
  @Path("/{db}/key/{key}/header/")
  @Timed
  def header(@PathParam("db") db: String, @PathParam("key") key: String) = {
    val select = repo.select(db, key)
    select.iterator.next().map(m => new Cell(m._1)).toArray
  }


  @GET
  @Path("/{db}/key/{key}/select/")
  @Timed
  def select(@PathParam("db") db: String, @PathParam("key") key: String) = {
    val select = repo.select(db, key)
    val head = new Header(select.iterator.next().map(m => new Cell(m._1)).toArray)
    val body = new Body(select.map(m => row(m)).toArray)
    new Table(key, head, body)
  }

  private def row(m:Map[String, AnyRef]) = new Row(m.values.map(c => new Cell(c)).toArray)

  @GET
  @Path("/{db}/key/{key}/view/")
  @Timed
  def selectView(@PathParam("db") db: String, @PathParam("key") key: String) = new SelectView(select(db, key))



}
