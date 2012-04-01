package pygala
package models

trait SourceParser {

  def colorCode(code: String, lang: String): Either[String, String]
}
