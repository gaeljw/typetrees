import ReleaseTransformations._
import xerial.sbt.Sonatype.sonatypeSettings
import xerial.sbt.Sonatype.sonatypeCentralHost

// Scala version(s)

val scala3Version = "3.3.6"

// Metadata

ThisBuild / organization := "io.github.gaeljw"
ThisBuild / organizationName := "gaeljw"
ThisBuild / organizationHomepage := Some(url("https://github.com/gaeljw"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/gaeljw/typetrees"),
    "scm:git@github.com:gaeljw/typetrees.git"
  )
)
ThisBuild / developers := List(
  Developer(
    "gaeljw",
    "Gaël Jourdan-Weil",
    "",
    url("https://github.com/gaeljw")
  )
)
ThisBuild / licenses := Seq(
  "Apache License 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.html")
)
ThisBuild / description := "Typetrees"
ThisBuild / homepage := Some(
  url("https://github.com/gaeljw/typetrees")
)

// Projects and settings

lazy val root = project
  .in(file("."))
  .settings(
    name := "typetrees",

    scalaVersion := scala3Version,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
  )

// Versioning policy

ThisBuild / versionScheme := Some("early-semver")
ThisBuild / versionPolicyIntention := Compatibility.BinaryAndSourceCompatible

// Release & Publish

Global / publishMavenStyle := true
Global / publishTo := sonatypePublishToBundle.value

// https://github.com/xerial/sbt-sonatype#using-with-sbt-release-plugin
releaseVersionBump := sbtrelease.Version.Bump.NextStable // Required since 1.4.0
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)

ThisBuild / sonatypeCredentialHost := sonatypeCentralHost
