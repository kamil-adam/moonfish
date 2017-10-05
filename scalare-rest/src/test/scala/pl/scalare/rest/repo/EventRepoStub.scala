package pl.scalare.rest.resources

import pl.scalare.core.model.Event
import pl.scalare.core.repo.EventRepo

class EventRepoStub extends EventRepo {
  override def createSomethingTable(): Unit = {}

  override def insert(event: Event): Unit = {}

  override def insert(id: Int, uuid: String, json: String): Unit = {}

  override def getAll: List[Event] = List()

  override def findById(id: Int): Event = new Event

  override def findByUuid(uuid: String): Event = new Event
}
