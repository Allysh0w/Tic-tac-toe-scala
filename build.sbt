ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

Compile / mainClass := Some("Game")

enablePlugins(AssemblyPlugin)
assembly / assemblyJarName := "tic-tac-toe-game.jar"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.13" % Test

lazy val root = (project in file("."))
  .settings(
    name := "tic-tac-toe-game"
  )
