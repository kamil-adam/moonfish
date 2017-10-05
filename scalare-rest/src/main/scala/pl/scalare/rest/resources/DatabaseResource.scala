package pl.scalare.rest.resources

import java.util.Optional
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{QueryParam, _}

import com.codahale.metrics.annotation.{ExceptionMetered, Metered, Timed}
import pl.scalare.core.model.OptDatas.optInt
import pl.scalare.core.model._
import pl.scalare.core.repo.SelectRepo
import pl.scalare.rest.ViewConfiguration
import pl.scalare.rest.views.{DatabasesView, KeysView, SelectView}


@Path("/databases")
@Produces(Array(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML))
class DatabaseResource @Inject()(@Inject val repo: SelectRepo, @Inject val rtc: ViewConfiguration) {

  @GET
  @Path("/view")
  @Timed
  def databasesView = new DatabasesView(rtc, databases)

  @GET
  @Path("/")
  @Timed @Metered @ExceptionMetered
  def databases = repo.databases.toArray

  @GET
  @Path("/{db}/keys/view")
  @Timed @Metered @ExceptionMetered
  def keysView(@PathParam("db") db: String) = new KeysView(rtc, keys(db))

  @GET
  @Path("/{db}/keys")
  @Timed @Metered @ExceptionMetered
  def keys(@PathParam("db") db: String) = repo.keys(db).toArray

  @GET
  @Path("/{db}/keys/{key}/header/")
  @Timed @Metered @ExceptionMetered
  def header(@PathParam("db") db: String, @PathParam("key") key: String) = {
    val select = repo.select(db, key, null)
    select.iterator.next().map(m => new Cell(m._1)).toArray
  }

  @GET
  @Path("/{db}/keys/{key}/view/")
  @Timed @Metered @ExceptionMetered
  def selectView(@PathParam("db") db: String,
                 @PathParam("key") key: String,
                 @QueryParam("limit") limit: Optional[Integer],
                 @QueryParam("offset") offset: Optional[Integer]) = new SelectView(rtc, select(db, key, limit, offset))

  @GET
  @Path("/{db}/keys/{key}/")
  @Timed @Metered @ExceptionMetered
  def select(@PathParam("db") db: String,
             @PathParam("key") key: String,
             @QueryParam("limit") limit: Optional[Integer],
             @QueryParam("offset") offset: Optional[Integer]) = {

    val optData = OptData(limit, offset)
    val select = repo.select(db, key, optData)
    val iterator = select.iterator
    if (iterator.hasNext) {
      val header = iterator.next().map(m => m._1).toArray
      val body = select
        .map(m => m
          .map(n => "" + n
            ._2)
          .toArray)
        .toArray
      new Select(db, key, header, body)
    } else
      new Select(db, key)
  }

  @POST
  @Path("/{db}/keys/{key}/")
  @Timed @Metered @ExceptionMetered
  def post(
            @QueryParam("driverClass") driverClass: String,
            @QueryParam("url") url: String,
            @QueryParam("user") user: String,
            @QueryParam("password") password: String): Unit = {

  }


}
