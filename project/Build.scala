import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "pygala"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      resolvers ++= Seq(
        "iliaz.com" at "http://scala.iliaz.com/"
        ),
      libraryDependencies ++= Seq(
        "org.scalaz" %% "scalaz-core" % "6.0.4",
        "com.github.ornicar" %% "scalalib" % "1.23"
        ),
      scalacOptions := Seq("-deprecation", "-unchecked")
    )

}
