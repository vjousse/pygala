package pygala
package models
import scalaz.effects._

trait SourceParser {

  def colorCode(code: String, lang: String): Either[String, IO[String]]

  def supportedFormats(): IO[Map[String, String]]
}
