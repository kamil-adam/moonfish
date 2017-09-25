sbt.version = 0.13 .13

lazy val commonSettings = Seq(
  organization := "pl.writeonly.son2",
  scalaVersion := "2.11.8",
  version := "2.8.3-SNAPSHOT"
)
lazy val root = (project in file("."))
  .settings(
    name := "son2",
    commonSettings,


    libraryDependencies += derby
  )
lazy val core = (project in file("main")).
  settings(
    // other settings
  )
lazy val util = (project in file("test")).
  settings(
    // other settings
  )
val derby = "org.apache.derby" % "derby" % "10.4.1.3"