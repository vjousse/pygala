package pygala
package pygment

import models.SourceParser
import util.FileUtil

import scala.sys.process._
import scalaz.effects._

case class Pygment(pygmentBin: String = "/urs/bin/pygmentize") extends SourceParser {

  def colorCode(code: String, lang: String): Either[String, IO[String]] = {
    //val command: String = pygmentBin + " -l " + lang + " -f html -O noclasses=True,linenos=table"
    val command: String = pygmentBin + " -l " + lang + " -f html"
    Right(
      for {
        file ← FileUtil.writeToTempFile("pygala", code)
      } yield ((command + " " + file.getAbsolutePath()).!!))
  }

  def supportedFormats(): IO[Map[String, String]] = io {
    parsePygmentList((pygmentBin + " -L lexer").!!)
  }

  def parsePygmentList(list: String): Map[String, String] = {
    val listString = augmentString(list).lines

    val headers = listString
      .filter(_.startsWith("* "))
      .map { line => line.drop(2).dropRight(1).toLowerCase }

    val descriptions = listString
      .filter(_.startsWith("    "))
      .map { _.trim }

    for {
      (key, value) <- headers.zip(descriptions).toMap
      explodedKey <- key.split(",")
    } yield(explodedKey.trim, value)

  }

}
