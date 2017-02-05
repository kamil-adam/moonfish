package pl.scalare.core.repo

import pl.scalare.core.model.Event

trait EventRepo {
  def createSomethingTable()

  def insert(event: Event)

  def insert(id: Int, uuid: String, json: String)

  def getAll: List[Event]

  def findById(id: Int): Event

}
