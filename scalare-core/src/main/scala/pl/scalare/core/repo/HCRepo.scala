package pl.scalare.core.repo

trait HCRepo {
  def runHealthChecks: Map[String,AnyRef]

  def runHealthCheckList: List[(String,AnyRef)]
}
