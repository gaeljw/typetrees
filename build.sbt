val scala3Version = "3.0.0-M3"

// TODO complete with all necessary stuff for publishing

lazy val root = project
  .in(file("."))
  .settings(
    name := "typetrees",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
  )
