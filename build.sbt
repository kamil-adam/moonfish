enablePlugins(com.lucidchart.sbt.scalafmt.ScalafmtPlugin)

lazy val dropwizardVersion = "1.1.4"
lazy val jacksonVersion = "2.8.3"
lazy val vaadinVersion = "8.1.2"

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
lazy val core = (project in file("main"))
  .configs(FunTest, FeatureTest)
  .settings(
    // other settings
  )
lazy val util = (project in file("test"))
  .configs(FunTest, FeatureTest)
  .settings(
    // other settings
  )
val derby = "org.apache.derby" % "derby" % "10.4.1.3"