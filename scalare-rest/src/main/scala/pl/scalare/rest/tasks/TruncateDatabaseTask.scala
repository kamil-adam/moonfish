package pl.scalare.rest.tasks

import java.io.PrintWriter
import javax.inject.Inject

import com.google.common.collect.ImmutableMultimap
import io.dropwizard.servlets.tasks.Task
import pl.scalare.impl.repo.database.SQLiteDatabase


class SnapshotTask @Inject()(val database: SQLiteDatabase) extends Task("truncate") {


  override def execute(parameters: ImmutableMultimap[String, String], output: PrintWriter): Unit = {
    //    this.database.truncate();
  }
}