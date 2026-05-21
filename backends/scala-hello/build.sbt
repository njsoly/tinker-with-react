ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.3"

lazy val root = (project in file("."))
  .settings(
    name := "scala-hello",
    idePackagePrefix := Some("com.syntj.scala.hello")
  )
