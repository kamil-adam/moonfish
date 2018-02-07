enablePlugins(com.lucidchart.sbt.scalafmt.ScalafmtPlugin)

resolvers += "Artima Maven Repository" at "http://repo.artima.com/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val dropwizardVersion = "1.0.6"
lazy val jacksonVersion = "2.7.8"
lazy val vaadinVersion = "8.1.2"
lazy val hsqldbVersion = "2.3.4"

lazy val versionSnapshot = "2.8.3-SNAPSHOT"

lazy val commonSettings = Seq(
  organization := "pl.writeonly.scalare",
  scalaVersion := "2.11.8",
  version := versionSnapshot
)
//lazy val FunJacoco = config("fun") extend(JacocoItPlugin)
lazy val FunTest = config("fun") extend(Test)
lazy val FeatureTest = config("feature") extend(Test)

lazy val funInConfig = inConfig(FunTest)(Defaults.testTasks)
lazy val featureInConfig = inConfig(FeatureTest)(Defaults.testTasks)

def whiteFilter(name: String): Boolean = (name endsWith "ResultSpec") || (name endsWith "ThrowsSpec")
def grayFilter(name: String): Boolean = (name endsWith "ScalarSpec") || (name endsWith "VectorSpec ")
def blackFilter(name: String): Boolean = (name endsWith "FeatureSpec")

lazy val whiteSetting = testOptions in Test := Seq(Tests.Filter(whiteFilter))
lazy val graySetting = testOptions in FunTest := Seq(Tests.Filter(grayFilter))
lazy val blackSetting = testOptions in FeatureTest := Seq(Tests.Filter(blackFilter))

lazy val inConfigs = Seq(funInConfig, featureInConfig)
lazy val settings = Seq(whiteSetting, graySetting, blackSetting)


lazy val root = (project in file("."))
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare",
    commonSettings,
    libraryDependencies += derby
  )
lazy val main = (project in file("scalare-main"))
  .dependsOn(rest)
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare-main",
    commonSettings,
    libraryDependencies ++= Seq(
      "com.google.inject" % "guice" % "4.1.0",
      "org.javatuples" % "javatuples" % "1.2"
    )
  )

lazy val rest = (project in file("scalare-rest"))
  .dependsOn(japi, impl)
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare-rest",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.dropwizard" % "dropwizard-auth" % dropwizardVersion,
      "io.dropwizard" % "dropwizard-views-mustache" % dropwizardVersion,
      "org.scalatra.scalate" % "scalate-core_2.11" % "1.8.0",
      "com.google.code.gson" % "gson" % "2.8.0",
      "org.javatuples" % "javatuples" % "1.2",
      "org.scalatra" % "scalatra_2.11" % "2.5.1"
    )
  )

lazy val japi = (project in file("scalare-japi"))
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare-japi",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.dropwizard" % "dropwizard-validation" % dropwizardVersion
    )
  )

lazy val impl = (project in file("scalare-impl"))
  .dependsOn(core, util)
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare-impl",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.dropwizard" % "dropwizard-jdbi" % dropwizardVersion,
      "io.dropwizard" % "dropwizard-client" % dropwizardVersion,
      "com.h2database" % "h2" % "1.4.193",
      "org.hsqldb" % "hsqldb" % hsqldbVersion,
      "org.hsqldb" % "sqltool" % hsqldbVersion,
      "org.apache.derby" % "derby" % "10.13.1.1",
      "org.xerial" % "sqlite-jdbc" % "3.16.1"
    )
  )

lazy val core = (project in file("scalare-core"))
  .dependsOn(spec)
  .configs(FunTest, FeatureTest)
  .settings(
    name := "scalare-core",
    commonSettings,
    libraryDependencies ++= Seq(
      "io.dropwizard" % "dropwizard-core" % dropwizardVersion
    )
  )


lazy val util = (project in file("scalare-util"))
  .dependsOn(spec)
  .settings(
    name := "scalare-util",
    commonSettings,
    libraryDependencies ++= Seq(
      "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
      "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion,
      "org.yaml" % "snakeyaml" % "1.18",
      "com.google.code.gson" % "gson" % "2.8.0"
    )
  )

lazy val spec = (project in file("scalare-spec"))
  .settings(
    name := "scalare-spec",
    commonSettings,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % "2.11.11",
      "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.7.2",
      "org.scalamock" % "scalamock-scalatest-support_2.11" % "3.6.0",
      "org.scalacheck" % "scalacheck_2.11" % "1.13.5",
      "org.seleniumhq.selenium" % "selenium-java" % "2.35.0",
      "org.pegdown" % "pegdown" % "1.6.0",
      "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  )

val derby = "org.apache.derby" % "derby" % "10.4.1.3"