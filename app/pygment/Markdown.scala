package pygala
package pygment

import util.FileUtil

import scala.sys.process._
import scalaz.effects._

case class Markdown(markdownBin: String) {

  def parse(markdown: String): Either[String, IO[String]] = {
    val command: String = markdownBin + " -f "
    Right(
      for {
        file <- FileUtil.writeToTempFile("markdown", markdown)
      } yield ((command + file.getAbsolutePath()).!!))
  }

}
