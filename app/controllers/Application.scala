package pygala
package controllers

import play.api._
import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import com.codahale.jerkson.Json.generate

import scalaz.effects._

object Application extends PygalaController {

  val codeForm = Form(
    tuple(
      "code" -> text,
      "lang" -> text))

  def highlight = Action { implicit request ⇒
    codeForm.bindFromRequest.fold(
      formWithErrors ⇒ BadRequest("Oh, that's bad."),
      value ⇒ env.supportedFormats.get(value._2.toLowerCase) match {
        case Some(_) ⇒ env.parser.colorCode(value._1, value._2).fold(
          error ⇒ BadRequest(error),
          code ⇒ Ok(code.unsafePerformIO))
        case None ⇒ BadRequest("Unsupported language to highlight: " + value._2)
      })
  }

  def lexers = Action {
    JsonOk(env.supportedFormats)
  }
}
