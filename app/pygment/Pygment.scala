package pygala
package pygment

import models.SourceParser

case class Pygment() extends SourceParser {

  def colorCode(code: String, lang: String): Either[String, String] = {
    Right("Code "+ code +" highlighted with lang "+ lang)
  }

}
