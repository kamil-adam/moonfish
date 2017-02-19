package pl.scalare.core.repo

trait TaskRepo {
  def tasks: Iterable[String]
}
