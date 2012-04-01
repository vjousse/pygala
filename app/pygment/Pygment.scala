package pygala
package pygment

import models.SourceParser

case class Pygment(pygmentBin: String) extends SourceParser {

  def colorCode(code: String, lang: String): Either[String, String] = {
    Right("Code " + code + " highlighted with lang " + lang)
  }

}
