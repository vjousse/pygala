package pygala
package pygment

import models.SourceParser

import java.io.File

import scala.sys.process._
import scalaz.effects._

case class Pygment(pygmentBin: String) extends SourceParser {

  def colorCode(code: String, lang: String): Either[String, IO[String]] = {
    //TODO: use http://www.scala-lang.org/api/milestone/scala/sys/process/ProcessBuilder.html
    //val command: String = "ls"
    val command: String = pygmentBin + " -l " + lang + " -f html"
    Right(
      for {
        file ← writeToTempFile("pygala", code)
      } yield ((command + " " + file.getAbsolutePath()).!!))
  }

  private def writeToTempFile(prefix: String, data: String): IO[File] = io {
    val file: File = File.createTempFile(prefix, ".tmp")
    file.deleteOnExit
    printToFile(file)(p ⇒ p.println(data))
    file
  }

  private def printToFile(f: java.io.File)(op: java.io.PrintWriter ⇒ Unit) {
    val p = new java.io.PrintWriter(f)
    try { op(p) } finally { p.close() }
  }
}
