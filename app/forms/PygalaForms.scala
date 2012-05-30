package pygala
package forms

import play.api.data._
import play.api.data.Forms._

case class PygalaForms(supportedFormats: Map[String, String]) {

  val codeForm = Form(
    tuple(
      "code" -> text,
      "lang" -> text) verifying ("Unsupported language to highlight", fields => fields match {
        case (c, l) => l == "auto" || supportedFormats.get(l.toLowerCase).isDefined
      }))

  val markdownForm = Form("markdown" -> text)

}
