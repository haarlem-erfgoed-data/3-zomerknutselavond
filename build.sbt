// Turn this project into a Scala.js project by importing these settings

organization := "nl.verdwenengebouwen"
name := "xPage App VerdwenenGebouwen"
normalizedName := "lostbuildings-smalldata"
version := "0.0"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
  "com.lihaoyi" %%% "scalatags" % "0.5.5",
  "com.lihaoyi" %%% "upickle" % "0.4.1",
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "io.surfkit" %%% "scalajs-google-maps" % "0.1-SNAPSHOT")

jsDependencies += "org.webjars" % "jquery" % "3.0.0" / "3.0.0/jquery.js"

lazy val root = (project in file(".")).enablePlugins(ScalaJSPlugin)
workbenchSettings
updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)

// Workbench has to know how to restart your application.
bootSnippet := "nl.verdwenengebouwen.Main.main();"
