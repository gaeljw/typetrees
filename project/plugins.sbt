// Formatting
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.6.2")

// Version policy check
addSbtPlugin("ch.epfl.scala" % "sbt-version-policy" % "3.3.0")

// Release & Publish (tag-based, brings in sbt-dynver and sbt-pgp)
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.12.1")
