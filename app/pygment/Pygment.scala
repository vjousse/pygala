package pygala
package pygment

import models.SourceParser

import java.io.File

import scala.sys.process._
import scalaz.effects._

case class Pygment(pygmentBin: String = "/urs/bin/pygmentize") extends SourceParser {

  def colorCode(code: String, lang: String): Either[String, IO[String]] = {
    //val command: String = pygmentBin + " -l " + lang + " -f html -O noclasses=True,linenos=table"
    val command: String = pygmentBin + " -l " + lang + " -f html"
    Right(
      for {
        file ← writeToTempFile("pygala", code)
      } yield ((command + " " + file.getAbsolutePath()).!!))
  }

  def supportedFormats(): IO[Map[String, String]] = io {
    val command: String = pygmentBin + " -L lexer"
    val result: String  = command.!!
    println(result)
    Map("scala" -> "todo")
  }

  def parsePygmentList(list: String): Map[String, String] = {
    Map("scala" -> "todo")
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
